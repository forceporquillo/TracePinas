/*
 * Created by Force Porquillo on 5/9/20 8:49 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/16/20 8:49 PM
 */

package com.force.codes.project.app.presentation_layer.views.fragments.worldwide;

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

import com.force.codes.project.app.R;
import com.force.codes.project.app.app.Injection;
import com.force.codes.project.app.databinding.FragmentWorldwideBinding;
import com.force.codes.project.app.factory.WorldwideViewModelFactory;
import com.force.codes.project.app.model.CountryDetails;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.FragmentCallback;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.OnRequestResponse;
import com.force.codes.project.app.presentation_layer.views.adapters.CountryAdapter;
import com.force.codes.project.app.presentation_layer.views.fragments.BaseFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.favorites.FavoritesFragment;
import com.force.codes.project.app.presentation_layer.views.fragments.statistics.StatisticsFragment;
import com.force.codes.project.app.service.executors.AppExecutors;
import com.force.codes.project.app.service.network.ConnectionCallback;
import com.force.codes.project.app.service.network.NetworkConnectivity;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class WorldwideFragment extends BaseFragment implements FragmentCallback, View.OnClickListener, ConnectionCallback, OnRequestResponse{

    //private static final String UPDATING = "Updating data from server…";
    //private static final String ERROR = "Data might not be updated";
    //private static final String FAILED = "Failed to update data";

    @BindView(R.id.swipe_fresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    //@BindView(R.id.shimmer_container)
    //ShimmerFrameLayout shimmerFrameLayout;

    // @BindView(R.id.customScrollView)
    // CustomScrollView scrollView;

    //@BindView(R.id.update)
    //TextView timeUpdate;

    //@BindView(R.id.favorites_button)
    //FloatingActionButton favoritesButton;

    // @BindView(R.id.custom_widget_container)
    // LinearLayout customWidget;

    protected FragmentWorldwideBinding binding;
    private WorldwideViewModel worldwideViewModel;
    private CountryAdapter countryAdapter;
    private Unbinder unbinder;

    private NetworkConnectivity connectivity;

    WorldwideFragment(){

    }

    public static Fragment newInstance(){
        return new WorldwideFragment();
    }

    @Override
    public void onInternetConnectionChanged(Boolean isConnected){
        if(!isConnected){
            swipeRefreshLayout().setEnabled(false);
            //timeUpdate.setText(ERROR);
        } else{
            swipeRefreshLayout().setEnabled(true);
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        connectivity.startConnection();
        worldwideViewModel.getDataFromDatabase().observe(this, countryDetails -> {
            if(countryDetails.isEmpty()){
                worldwideViewModel.forceUpdate();
            } else{
                countryAdapter.submitList(countryDetails);
                refreshLayout.setEnabled(true);
                refreshLayout.setRefreshing(false);
                recyclerView.setAdapter(countryAdapter);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        connectivity = new NetworkConnectivity(this);
        countryAdapter = new CountryAdapter(this);
        WorldwideViewModelFactory modelFactory = Injection
                .providesViewModelFactory(this, getContext(), new AppExecutors());
        worldwideViewModel = new ViewModelProvider(this, modelFactory)
                .get(WorldwideViewModel.class);
        worldwideViewModel.getDataFromDatabase();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_worldwide, container, false);
        binding.invalidateAll();
        binding.setViewModel(worldwideViewModel);
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
        //favoritesButton.setOnClickListener(this);
        setRecyclerView();
    }

    /*private void setAnimationUtils(View root){
        //Animation slideDownAnim = AnimationUtils.loadAnimation(root.getContext(), R.anim.pop_up_slide_down);
        //Animation slideUpAnim = AnimationUtils.loadAnimation(root.getContext(), R.anim.pop_up_slide_up);
    }*/

    @Override
    public void onErrorResponse(Boolean onErrorRequest){
        if(onErrorRequest){
            swipeRefreshLayout().setRefreshing(false);
            //timeUpdate.setText(FAILED);
        }
    }

    private SwipeRefreshLayout swipeRefreshLayout(){
        refreshLayout.setColorSchemeResources(
                R.color.blue, R.color.green, R.color.red
        );

        refreshLayout.setOnRefreshListener(() -> {
            worldwideViewModel.getDataFromNetwork();
            //timeUpdate.setText(UPDATING);
        });

        return refreshLayout;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        //shimmerFrameLayout.stopShimmer();
        Timber.d("onPause called");
        super.onPause();
    }

    @Override
    public void onStop(){
        //shimmerFrameLayout.onDetachedFromWindow();
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
        Fragment fragment = FavoritesFragment.newInstance();
        super.setDelegateFragment(fragment).commit();
    }

    @Override
    public void CustomCardViewListener(int position){
        Fragment fragment = StatisticsFragment.newInstance();
        super.setDelegateFragment(fragment).commit();
    }

    @Override
    public void insertOrRemoveFavorites(CountryDetails details){
        new AppExecutors().networkIO().execute(() ->
                worldwideViewModel.addOrRemoveFavorites(details));
    }

    private void setRecyclerView(){
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