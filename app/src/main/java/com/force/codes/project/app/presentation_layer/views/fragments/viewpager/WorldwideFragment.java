/*
 * Created by Force Porquillo on 5/9/20 8:49 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/17/20 8:49 PM
 */

package com.force.codes.project.app.presentation_layer.views.fragments.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.FragmentWorldwideBinding;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.FragmentCallback;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.OnRequestResponse;
import com.force.codes.project.app.presentation_layer.views.adapters.CountryAdapter;
import com.force.codes.project.app.presentation_layer.views.fragments.BaseFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.StatisticsFragment;
import com.force.codes.project.app.presentation_layer.views.viewmodels.WorldwideViewModel;
import com.force.codes.project.app.presentation_layer.views.viewmodels.factory.ViewModelProviderFactory;
import com.force.codes.project.app.service.executors.AppExecutors;
import com.force.codes.project.app.service.network.ConnectionCallback;
import com.force.codes.project.app.service.network.NetworkConnectivity;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class WorldwideFragment extends BaseFragment implements FragmentCallback, View.OnClickListener, ConnectionCallback, OnRequestResponse{

    @BindView(R.id.swipe_fresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.shimmer_layout)
    ShimmerFrameLayout shimmer;

    private FragmentWorldwideBinding binding;
    private WorldwideViewModel viewModel;
    private NetworkConnectivity connectivity;
    private CountryAdapter countryAdapter;

    private Unbinder unbinder;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    AppExecutors appExecutors;

    public WorldwideFragment(){

    }

    public static Fragment newInstance(){
        return new WorldwideFragment();
    }

    @Override
    public void onInternetConnectionChanged(Boolean isConnected){
        if(!isConnected)
            swipeRefreshLayout().setEnabled(false);
        else
            swipeRefreshLayout().setEnabled(true);
    }

    @Override
    public void onStart(){
        super.onStart();
        viewModel.getDataFromDatabase().observe(this, countryDetails -> {
            if(countryDetails.isEmpty()){
                viewModel.getDataFromNetwork();
            }else{
                countryAdapter.submitList(countryDetails);
                refreshLayout.setEnabled(true);
                refreshLayout.setRefreshing(false);
                shimmer.stopShimmer();
                recyclerView.setAdapter(countryAdapter);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        connectivity = new NetworkConnectivity(this);
        countryAdapter = new CountryAdapter(this);
        viewModel = new ViewModelProvider(this, factory).get(WorldwideViewModel.class);
        viewModel.getDataFromNetwork();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_worldwide, container, false);
        binding.invalidateAll();
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        final View root = binding.getRoot();
        unbinder = ButterKnife.bind(this, root);
        setRetainInstance(true);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout().setEnabled(false);
        setRecyclerView();
        shimmer.startShimmer();
    }

    @Override
    public void onErrorResponse(Boolean onErrorRequest){
        if(onErrorRequest){
            swipeRefreshLayout().setRefreshing(false);
        }
    }

    private SwipeRefreshLayout swipeRefreshLayout(){
        refreshLayout.setColorSchemeResources(
                R.color.blue, R.color.blue, R.color.blue);

        refreshLayout.setOnRefreshListener(() ->
                viewModel.getDataFromNetwork());

        return refreshLayout;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        Timber.d("onPause called");
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Timber.d("onDestroy called");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();

        if(recyclerView.getAdapter() != null){
            recyclerView.setAdapter(null);
        }

        Timber.d("onDestroyView called");
        connectivity.destroyConnection();
        binding.unbind();
        binding = null;
        unbinder.unbind();
    }

    @Override
    public void onClick(View v){
        //Fragment fragment = HelpCenterFragment.newInstance();
        //super.setDelegateFragment(fragment).commit();
    }

    @Override
    public void cardItemListener(int position){
        Fragment fragment = StatisticsFragment.newInstance();
        //super.setDelegateFragment(fragment).commit();
    }

    final void setRecyclerView(){
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));

        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL));

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setAdapter(countryAdapter);
    }
}