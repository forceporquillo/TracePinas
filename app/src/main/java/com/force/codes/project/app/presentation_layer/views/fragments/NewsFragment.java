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
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.news.ArticlesItem;
import com.force.codes.project.app.data_layer.model.twitter.TwitterData;
import com.force.codes.project.app.presentation_layer.controller.custom.model.Group;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.NewsItemCallback;
import com.force.codes.project.app.presentation_layer.views.adapters.HeaderNewsAdapter;
import com.force.codes.project.app.presentation_layer.views.adapters.HotNewsAdapter;
import com.force.codes.project.app.presentation_layer.views.adapters.NewsGroupAdapter;
import com.force.codes.project.app.presentation_layer.views.viewmodels.NewsViewModel;
import com.force.codes.project.app.presentation_layer.views.viewmodels.factory.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends BaseFragment implements NewsItemCallback{
    public NewsFragment(){
        // Required empty public constructor
    }

    @BindView(R.id.news_recycler_view)
    RecyclerView recyclerView;

    @Inject
    ViewModelProviderFactory factory;
    private List<ArticlesItem> articlesItemList;
    private NewsViewModel newsViewModel;
    private HotNewsAdapter hotNewsAdapter;
    private Unbinder unbinder;
    private List<TwitterData> twitterDataList;
    private HeaderNewsAdapter headerNewsAdapter;

    public static NewsFragment newInstance(){
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        hotNewsAdapter = new HotNewsAdapter(this);
        headerNewsAdapter = new HeaderNewsAdapter(this);
        newsViewModel = new ViewModelProvider(this, factory).get(NewsViewModel.class);
        newsViewModel.forceUpdate();
    }

    @Override
    public void onStart(){
        super.onStart();
        newsViewModel.pagedListLiveData().observe(this, newsList -> {
            if(newsList != null && newsList.size() != 0){
                hotNewsAdapter.setItemList(newsList);
                articlesItemList = newsList;
                hotNewsAdapter.notifyDataSetChanged();
                return;
            }
            newsViewModel.getNewsData();
        });

        newsViewModel.pageListTwitterData().observe(this, twitterResponses -> {
            if(twitterResponses != null){
                headerNewsAdapter.submitList(twitterResponses);
                headerNewsAdapter.notifyDataSetChanged();
                twitterDataList = twitterResponses;
                return;
            }
            newsViewModel.getTwitterUserTimeline();
        });
    }

    // get callback item index position in hotNews adapter.
    @Override
    public void hotNewsItemListener(int position){
        ArticlesItem articlesItem = articlesItemList.get(position);
        super.setFragment(ReadNewsFragment.newInstance(articlesItem));
    }

    @Override
    public void headerNewsItemListener(int position){
        String url;

        if(twitterDataList.get(position).getEntities().getMedia() != null)
            url = twitterDataList.get(position).getEntities().getMedia().get(0).getExpandedUrl();
        else url = "https://twitter.com/tito_4s";

        assert getActivity() != null;
        Intent intent;
        try{
            // get the Twitter app if possible
            getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }catch(Exception e){
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            Timber.e(e);
        }

        ActivityOptions activityOptions = ActivityOptions.makeCustomAnimation(getContext(),R.anim.push_in, R.anim.push_out);
        getActivity().startActivity(intent, activityOptions.toBundle());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        setRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState != null){
            // do something
        }

        assert getActivity() != null;
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setRecyclerView(){
        NewsGroupAdapter groupAdapter = new NewsGroupAdapter(headerNewsAdapter, hotNewsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(groupAdapter);
    }

    private static ArrayList<Group> groups(){
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group("Recent Tweets"));
        groups.add(new Group("Hot News"));
        return groups;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        articlesItemList.clear();
        //twitterResponseList.clear();
    }
}
