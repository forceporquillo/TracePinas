/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:01 AM
 */

package com.force.codes.project.app.presentation_layer.views.fragments;

/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:24 AM
 *
 */

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.news.ArticlesItem;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;
import com.force.codes.project.app.databinding.FragmentNewsBinding;
import com.force.codes.project.app.presentation_layer.controller.interfaces.NewsItemCallback;
import com.force.codes.project.app.presentation_layer.controller.utils.Utils;
import com.force.codes.project.app.presentation_layer.controller.utils.NetworkUtils;
import com.force.codes.project.app.presentation_layer.controller.utils.AppExecutors;
import com.force.codes.project.app.presentation_layer.views.adapters.HeaderNewsAdapter;
import com.force.codes.project.app.presentation_layer.views.adapters.HotNewsAdapter;
import com.force.codes.project.app.presentation_layer.views.adapters.NewsGroupAdapter;
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory;
import com.force.codes.project.app.presentation_layer.views.viewmodels.NewsViewModel;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

import static com.force.codes.project.app.presentation_layer.controller.utils.Utils.animate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends BaseFragment
    implements NewsItemCallback, SwipeRefreshLayout.OnRefreshListener {
  private static final String RECENT_TWEETS_ARGS = "HEADER_ARGS";
  private static final String HOT_NEWS_ARGS = "HOT_NEWS_ARGS";
  @Inject
  ViewModelProviderFactory factory;
  private List<ArticlesItem> articlesItemList;
  private List<TwitterData> twitterDataList;
  private NewsViewModel newsViewModel;
  private HotNewsAdapter hotNewsAdapter;
  private HeaderNewsAdapter headerNewsAdapter;
  private FragmentNewsBinding binding;
  private NetworkUtils connectivity;
  private Context context;

  public NewsFragment() {
    // Required empty public constructor
  }

  public static NewsFragment newInstance() {
    NewsFragment fragment = new NewsFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  private static boolean isPagedListEmpty(@NotNull List<?> list) {
    Timber.e(String.valueOf(list.size()));
    return list.size() != 0;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      hotNewsAdapter = new HotNewsAdapter(this);
      headerNewsAdapter = new HeaderNewsAdapter(this);
      newsViewModel = new ViewModelProvider(this, factory).get(NewsViewModel.class);
      newsViewModel.forceUpdate();
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    connectivity.setOnNetworkListener(this);
    newsViewModel.pageListTwitterData().observe(this, twitterResponse -> {
      if (isPagedListEmpty(twitterResponse)) {
        headerNewsAdapter.submitList(twitterResponse);
        twitterDataList = twitterResponse;
        binding.swipeFresh.setRefreshing(false);
      }
    });

    newsViewModel.pagedListLiveData().observe(this, newsList -> {
      if (isPagedListEmpty(newsList)) {
        hotNewsAdapter.submitList(newsList);
        articlesItemList = newsList;
        binding.swipeFresh.setRefreshing(false);
      }
    });
  }

  @Override public void hotNewsItemListener(int position) {
    super.setFragment(ReadNewsFragment.newInstance(articlesItemList.get(position))).commit();
  }

  @Override
  public void headerNewsItemListener(int position) {
    String url = null;
    if (isPagedListEmpty(twitterDataList)) {
      try {
        url = Objects.requireNonNull(twitterDataList.get(position)
            .getEntities()).getMedia().get(0).getExpandedUrl();
      } catch (NullPointerException e) {
        url = "https://twitter.com/" + twitterDataList.get(position).getUser().getScreenName();
      }
    }
    assert getActivity() != null;
    Intent intent;
    try {
      // get the Twitter app if possible
      getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
      intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    } catch (Exception e) {
      // no Twitter app, revert to browser
      intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }
    ActivityOptions activityOptions = ActivityOptions
        .makeCustomAnimation(getContext(), R.anim.push_in, R.anim.push_out);
    getActivity().startActivity(intent, activityOptions.toBundle());
  }

  @Override public View
  onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentNewsBinding.inflate(inflater, container, false);
    binding.setNews(this);
    binding.setLifecycleOwner(this);
    binding.setVariable(BR.news, this);
    binding.executePendingBindings();
    return binding.getRoot();
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    setRecyclerView();
    setSwipeRefresh();

    if (savedInstanceState != null) {
      savedInstanceState.getParcelableArrayList(RECENT_TWEETS_ARGS);
      savedInstanceState.getParcelableArrayList(HOT_NEWS_ARGS);
    }

    connectivity = getNetworkUtils();
  }

  @Override
  public void onRefresh() {
    newsViewModel.forceUpdate();
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
    NewsGroupAdapter groupAdapter = new NewsGroupAdapter(headerNewsAdapter, hotNewsAdapter);
    binding.newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    binding.newsRecyclerView.setAdapter(groupAdapter);
  }

  public void setSwipeRefresh() {
    binding.swipeFresh.setOnRefreshListener(this);
    binding.swipeFresh.setColorSchemeColors(ContextCompat
        .getColor(binding.swipeFresh.getContext(), R.color.blue));
  }

  @RequiresApi(api = Build.VERSION_CODES.M) @Override public void onResume() {
    super.onResume();
    if(Utils.requiresSdkInt(Build.VERSION_CODES.M)){
      connectivity.startNetworkConnectivity();
    }

    if (newsViewModel.getOnError().get()) {
      binding.swipeFresh.setRefreshing(false);
      Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT)
          .show();
    }
  }

  @Override public void onNetworkConnectionChanged(Connectivity connectivity) {
    if (!connectivity.available()) {
      binding.network.networkParent.setAnimation(animate(true, context));
      new AppExecutors(100).threadDelay()
          .execute(() -> binding.network.networkParent.setVisibility(View.VISIBLE));
      Toast.makeText(getContext(), "Data might not be updated", Toast.LENGTH_SHORT).show();
    } else {
      binding.network.networkParent.setAnimation(animate(false, context));
      binding.network.networkParent.setVisibility(View.GONE);
    }

    // refresh UI state since we update the network views.
    binding.executePendingBindings();
  }

  @Override public void onInternetConnectionChanged(Boolean connected) {

  }
}
