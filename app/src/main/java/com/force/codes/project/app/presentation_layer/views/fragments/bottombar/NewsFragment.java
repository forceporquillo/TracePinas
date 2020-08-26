/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:01 AM
 */

package com.force.codes.project.app.presentation_layer.views.fragments.bottombar;

/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:24 AM
 *
 */

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.BuildConfig;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.news.ArticlesItem;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;
import com.force.codes.project.app.databinding.FragmentNewsBinding;
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener;
import com.force.codes.project.app.presentation_layer.controller.service.ThreadExecutor;
import com.force.codes.project.app.presentation_layer.controller.utils.NetworkUtils;
import com.force.codes.project.app.presentation_layer.controller.utils.Utils;
import com.force.codes.project.app.presentation_layer.views.adapters.HeaderNewsAdapter;
import com.force.codes.project.app.presentation_layer.views.adapters.HotNewsAdapter;
import com.force.codes.project.app.presentation_layer.views.adapters.NewsGroupAdapter;
import com.force.codes.project.app.presentation_layer.views.base.BaseFragment;
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory;
import com.force.codes.project.app.presentation_layer.views.fragments.ReadNewsFragment;
import com.force.codes.project.app.presentation_layer.views.viewmodels.NewsViewModel;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

import static com.force.codes.project.app.presentation_layer.controller.utils.Utils.animationUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends BaseFragment implements
    StackEventListener.NewsItemCallback, SwipeRefreshLayout.OnRefreshListener {
  private static final String RECENT_TWEETS_ARGS = "HEADER_ARGS";

  private static final String HOT_NEWS_ARGS = "HOT_NEWS_ARGS";

  private static final String BOOLEAN_ARGS = "BOOLEAN_ARGS";

  private static boolean isConnected = false;

  private List<ArticlesItem> articlesItemList;

  private List<TwitterData> twitterDataList;

  private NewsViewModel newsViewModel;

  private HotNewsAdapter hotNewsAdapter;

  private HeaderNewsAdapter headerNewsAdapter;

  private FragmentNewsBinding binding;

  private NetworkUtils connectivity;

  private ObservableBoolean observableBoolean = new ObservableBoolean();

  @Inject
  ViewModelProviderFactory factory;

  public NewsFragment() {
    // Required empty public constructor
  }

  public static NewsFragment newInstance(boolean updateData) {
    NewsFragment fragment = new NewsFragment();
    Bundle args = new Bundle();
    args.putBoolean(BOOLEAN_ARGS, updateData);
    fragment.setArguments(args);
    return fragment;
  }

  private static boolean isPagedListEmpty(@NotNull List<?> list) {
    return list.size() != 0;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      connectivity = getNetworkUtils();
      hotNewsAdapter = new HotNewsAdapter(this);
      headerNewsAdapter = new HeaderNewsAdapter(this);
      newsViewModel = new ViewModelProvider(this, factory).get(NewsViewModel.class);
    }
    connectivity.setOnNetworkListener(this);
  }

  public void startListeningNetwork() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      Timber.d("listening to upstream network chain");
      connectivity.startNetworkConnectivity();
      return;
    }
    Timber.d("listening to startInternetConnectivity");
    connectivity.startInternetConnectivity();
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (getArguments() != null && getArguments().getBoolean(BOOLEAN_ARGS)) {
      if (observableBoolean.get()) {
        newsViewModel.forceUpdate();
        Timber.d("updating...");
      }
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.M) @Override
  public void onStart() {
    super.onStart();
    startListeningNetwork();
    new ThreadExecutor(500).delayUIThread().execute(() -> {
      newsViewModel.getTwitterLiveData().observe(this, twitterDataPagedList -> {
        if (isPagedListEmpty(twitterDataPagedList)) {
          headerNewsAdapter.submitList((PagedList<TwitterData>)
              (twitterDataList = twitterDataPagedList)
          );
          binding.swipeFresh.setRefreshing(false);
        }
      });

      newsViewModel.getNewsLiveData().observe(this, articlesItems -> {
        if (isPagedListEmpty(articlesItems)) {
          hotNewsAdapter.submitList((PagedList<ArticlesItem>)
              (articlesItemList = articlesItems)
          );
          binding.swipeFresh.setRefreshing(false);
        }
      });
    });
  }

  @Override public void onResume() {
    super.onResume();
    if (newsViewModel.getOnError().get()) {
      binding.swipeFresh.setRefreshing(false);
      Toast.makeText(getContext(),
          R.string.check_connection,
          Toast.LENGTH_SHORT).show();
    }
  }

  @Override public View
  onCreateView(@NotNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentNewsBinding.inflate(inflater, container, false);
    binding.setNews(this);
    binding.setLifecycleOwner(this);
    binding.setVariable(BR.news, this);
    binding.executePendingBindings();
    return binding.getRoot();
  }

  @Override public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setRecyclerView();
    setSwipeRefresh(view);
    if (savedInstanceState != null) {
      savedInstanceState.getParcelableArrayList(RECENT_TWEETS_ARGS);
      savedInstanceState.getParcelableArrayList(HOT_NEWS_ARGS);
    }
  }

  @Override
  public void onRefresh() {
    if (isConnected) {
      newsViewModel.forceUpdate();
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding.unbind();
    binding = null;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    headerNewsAdapter = null;
    hotNewsAdapter = null;
    newsViewModel = null;
    connectivity.stopConnection();
  }

  private void setRecyclerView() {
    final NewsGroupAdapter groupAdapter =
        new NewsGroupAdapter(
            headerNewsAdapter,
            hotNewsAdapter
        );
    binding.newsRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext())
    );
    binding.newsRecyclerView.setAdapter(groupAdapter);
  }

  public void setSwipeRefresh(final View view) {
    binding.swipeFresh.setOnRefreshListener(this);
    binding.swipeFresh.setColorSchemeColors(ContextCompat
        .getColor(view.getContext(), R.color.blue));
  }

  @Override public void onRecentTweetsListener(int index) {
    String twitterUrl = null;
    Intent twitterIntent = null;
    if (isPagedListEmpty(twitterDataList)) {
      try {
        twitterUrl = Objects.requireNonNull(twitterDataList
            .get(index).getEntities())
            .getMedia()
            .get(0)
            .getExpandedUrl();
      } catch (NullPointerException e) {
        twitterUrl = getResources().getString(R.string.twitter_url)
            .concat(twitterDataList.get(index)
                .getUser()
                .getScreenName()
            ).toLowerCase();
      }
    }
    final String packageInfo = getResources().getString(R.string.package_name);
    assert getActivity() != null;
    try {
      getActivity().getPackageManager().getPackageInfo(packageInfo, 0);
      twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl))
          .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    } catch (PackageManager.NameNotFoundException e) {
      if (BuildConfig.DEBUG) {
        Timber.e(e, getString(R.string.twitter_app_message), Utils.getDeviceModel());
      }
      twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
    } finally {
      getActivity().startActivity(twitterIntent, Utils.customAnim(
          getContext(), R.anim.push_in, R.anim.push_out).toBundle());
    }
  }

  @Override public void onLocalNewsListener(int index) {
    super.setFragment(ReadNewsFragment.newInstance(
        articlesItemList.get(index))
    ).commit();
  }

  private static final int DELAY_MILL = 100;

  @Override public void onNetworkConnectionChanged(Connectivity connectivity) {
    final RelativeLayout networkBanner = binding.network.relativeLayout;
    if (isConnected = !connectivity.available()) {
      networkBanner.setAnimation(animationUtils(true, getContext()));
      new ThreadExecutor(DELAY_MILL)
          .delayUIThread()
          .execute(() -> {
            networkBanner.setVisibility(View.VISIBLE);
            Timber.i("Thread : " +
                Thread.currentThread() +
                "sleeps for %s", DELAY_MILL
            );
          });
    } else {
      networkBanner.setAnimation(animationUtils(false, getContext()));
      networkBanner.setVisibility(View.GONE);
    }
    // refresh UI state since we update the network views.
    binding.executePendingBindings();
  }

  @Override public void onInternetConnectionChanged(Boolean connected) {
   observableBoolean.set(connected);
  }
}
