package com.force.codes.project.covid19.controller.views.fragments.global;

/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 11:52 AM
 *
 */

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.force.codes.project.covid19.R;
import com.force.codes.project.covid19.controller.components.CountryStatisticsFragment;
import com.force.codes.project.covid19.controller.custom.interfaces.FragmentCallback;
import com.force.codes.project.covid19.controller.custom.views.CustomScrollView;
import com.force.codes.project.covid19.controller.factory.GlobalViewModelFactory;
import com.force.codes.project.covid19.controller.views.adapter.CountryListAdapter;
import com.force.codes.project.covid19.module.Injection;
import com.force.codes.project.covid19.service.executors.AppExecutors;
import com.force.codes.project.covid19.service.network.Connectivity;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class GlobalFragment extends Fragment implements FragmentCallback{
    // region CONSTANTS
    private static final String TAG = "WorldwideFragment";
    private static final String SNACK_BAR_MESSAGE = "No internet connection";
    private static final String RETRY = "Retry";

    @BindView(R.id.swipe_fresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.shimmer_container)
    ShimmerFrameLayout shimmerFrameLayout;

    @BindView(R.id.customScrollView)
    CustomScrollView scrollView;
    @BindView(R.id.overview_container)
    RelativeLayout overviewContainer;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;
    RelativeLayout relativeLayout;
    private CompositeDisposable compositeDisposable;
    private Connectivity connectivity = new Connectivity();
    // endregion
    private CountryListAdapter adapter;
    private GlobalViewModel globalViewModel;
    private Unbinder unbinder;

    private View view;

    public static Fragment newInstance(){
        return new GlobalFragment();
    }

    @Override
    public void onDestroyView(){
        shimmerFrameLayout.onDetachedFromWindow();
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onStart(){
        super.onStart();
        Timber.d("onStart: called");
        globalViewModel.getModelLiveData()
                .observe(this, countryDetails -> adapter.setList(countryDetails));

        scrollView.setEnableScrolling(true);
        refreshLayout.setEnabled(true);
    }

    private Snackbar showSnackBar(){
        return Snackbar.make(overviewContainer.getRootView(), SNACK_BAR_MESSAGE,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(RETRY, v -> globalViewModel.provideObservableData())
                .setText("Cannot fetch data from server. Please check your internet connection.")
                .setActionTextColor(getResources().getColor(R.color.blue));
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Timber.d("onCreate: called");
        compositeDisposable = new CompositeDisposable();
        adapter = new CountryListAdapter(this);
        GlobalViewModelFactory modelFactory = Injection.providesViewModelFactory(getContext(), new AppExecutors());
        globalViewModel = new ViewModelProvider(this, modelFactory).get(GlobalViewModel.class);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_worldwide, container, false);
        setRetainInstance(true);
        unbinder = ButterKnife.bind(this, view);
        shimmerFrameLayout.startShimmer();
        showSnackBar().show();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        refreshLayout.setEnabled(false);
        refreshLayout.setColorSchemeResources(R.color.blue);
        refreshLayout.setOnRefreshListener(() -> {
            //countryViewModel.getDataFromService();
            Timber.i("onViewCreated: called");
        });
        setRecyclerView();
        // disable scrollView when no data in UI
        scrollView.setEnableScrolling(false);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onPause(){
        shimmerFrameLayout.stopShimmer();
        Timber.d("onPause: called");
        super.onPause();
    }

    @Override
    public void CustomCardViewListener(int position){
        Fragment fragment = new CountryStatisticsFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                R.anim.enter_from_left, R.anim.exit_to_right).add(R.id.fragment_container, fragment)
                .addToBackStack(TAG).commit();
    }

    @Override
    public void onResume(){
        super.onResume();
        Timber.i("onResume called");
    }

    private void setRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemViewCacheSize(20);
    }
}
