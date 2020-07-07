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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.testmodel.Group;
import com.force.codes.project.app.data_layer.testmodel.Models;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.NewsItemCallback;
import com.force.codes.project.app.presentation_layer.views.adapters.NewsGroupAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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

    private Unbinder unbinder;

    public static NewsFragment newInstance(){
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

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
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setRecyclerView(){
        NewsGroupAdapter groupAdapter = new NewsGroupAdapter(groups(), latestModels(), hotModels(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(groupAdapter);
    }

    private static ArrayList<Group> groups(){
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group("Latest News"));
        groups.add(new Group("Hot News"));
        return groups;
    }

    private static ArrayList<Models> latestModels(){
        ArrayList<Models> latest = new ArrayList<>();

        latest.add(new Models("SpaceX is about to make history by relaunching a used Falcon 9 rocket", "Tito 4S, July 6, 2020", "https://cdn.vox-cdn.com/thumbor/Oi8KWigEsEg5h5n7dgEDJ7QiRic=/0x0:3000x2000/920x613/filters:focal(1260x760:1740x1240):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/53935213/25787998624_3ca213be1e_o.0.jpg"));
        latest.add(new Models("SpaceX Crew Dragon spacecraft docking at the ISS", "Tito 4S, July 6, 2020", "https://timesofsandiego.com/wp-content/uploads/2019/03/Crew-Dragon.jpg"));
        latest.add(new Models("Barring a surprise, SpaceX's Falcon Heavy", "Tito 4S, July 6, 2020", "https://cbsnews2.cbsistatic.com/hub/i/r/2018/02/04/29e49d64-a291-47b2-ad22-652ae6716ef9/thumbnail/620x398g2/ca4631d41fc14c8475f7ff3f1037ee1e/010918-heavy.jpg"));
        latest.add(new Models("Bob and Doug: best friends on historic SpaceX-NASA mission", "Tito 4S, July 6, 2020", "https://media.gq.com/photos/5ecfe04091d7f9d7fa10db02/16:9/w_2560%2Cc_limit/SpaceX-Space-Suits-gq-may-2020--.jpg"));
        latest.add(new Models("A Faulty Booster Might Have Sabotaged a Soyuz Rocket Launch", "Tito 4S, July 6, 2020", "https://media.wired.com/photos/5bbf72c46278de2d2123485b/master/w_2560%2Cc_limit/soyuz-1051882240.jpg"));
        return latest;
    }

    private static ArrayList<Models> hotModels(){
        ArrayList<Models> latest = new ArrayList<>();

        latest.add(new Models("SpaceX is about to make history by relaunching a used Falcon 9 rocket", "Cape Canaveral, FL. July 6, 2020", "https://cbsnews2.cbsistatic.com/hub/i/r/2018/02/04/29e49d64-a291-47b2-ad22-652ae6716ef9/thumbnail/620x398g2/ca4631d41fc14c8475f7ff3f1037ee1e/010918-heavy.jpg"));
        latest.add(new Models("SpaceX is about to make history by relaunching a used Falcon 9 rocket", "Cape Canaveral, FL. July 6, 2020", "https://cbsnews2.cbsistatic.com/hub/i/r/2018/02/04/29e49d64-a291-47b2-ad22-652ae6716ef9/thumbnail/620x398g2/ca4631d41fc14c8475f7ff3f1037ee1e/010918-heavy.jpg"));
        latest.add(new Models("SpaceX is about to make history by relaunching a used Falcon 9 rocket", "Cape Canaveral, FL. July 6, 2020", "https://cbsnews2.cbsistatic.com/hub/i/r/2018/02/04/29e49d64-a291-47b2-ad22-652ae6716ef9/thumbnail/620x398g2/ca4631d41fc14c8475f7ff3f1037ee1e/010918-heavy.jpg"));
        latest.add(new Models("SpaceX is about to make history by relaunching a used Falcon 9 rocket", "Cape Canaveral, FL. July 6, 2020", "https://cbsnews2.cbsistatic.com/hub/i/r/2018/02/04/29e49d64-a291-47b2-ad22-652ae6716ef9/thumbnail/620x398g2/ca4631d41fc14c8475f7ff3f1037ee1e/010918-heavy.jpg"));
        latest.add(new Models("SpaceX is about to make history by relaunching a used Falcon 9 rocket", "Cape Canaveral, FL. July 6, 2020", "https://cbsnews2.cbsistatic.com/hub/i/r/2018/02/04/29e49d64-a291-47b2-ad22-652ae6716ef9/thumbnail/620x398g2/ca4631d41fc14c8475f7ff3f1037ee1e/010918-heavy.jpg"));
        latest.add(new Models("SpaceX is about to make history by relaunching a used Falcon 9 rocket", "Cape Canaveral, FL. July 6, 2020", "https://cbsnews2.cbsistatic.com/hub/i/r/2018/02/04/29e49d64-a291-47b2-ad22-652ae6716ef9/thumbnail/620x398g2/ca4631d41fc14c8475f7ff3f1037ee1e/010918-heavy.jpg"));
        latest.add(new Models("SpaceX is about to make history by relaunching a used Falcon 9 rocket", "Cape Canaveral, FL. July 6, 2020", "https://cbsnews2.cbsistatic.com/hub/i/r/2018/02/04/29e49d64-a291-47b2-ad22-652ae6716ef9/thumbnail/620x398g2/ca4631d41fc14c8475f7ff3f1037ee1e/010918-heavy.jpg"));
        latest.add(new Models("SpaceX is about to make history by relaunching a used Falcon 9 rocket", "Cape Canaveral, FL. July 6, 2020", "https://cbsnews2.cbsistatic.com/hub/i/r/2018/02/04/29e49d64-a291-47b2-ad22-652ae6716ef9/thumbnail/620x398g2/ca4631d41fc14c8475f7ff3f1037ee1e/010918-heavy.jpg"));
        latest.add(new Models("SpaceX is about to make history by relaunching a used Falcon 9 rocket", "Cape Canaveral, FL. July 6, 2020", "https://cbsnews2.cbsistatic.com/hub/i/r/2018/02/04/29e49d64-a291-47b2-ad22-652ae6716ef9/thumbnail/620x398g2/ca4631d41fc14c8475f7ff3f1037ee1e/010918-heavy.jpg"));
        latest.add(new Models("SpaceX is about to make history by relaunching a used Falcon 9 rocket", "Cape Canaveral, FL. July 6, 2020", "https://cbsnews2.cbsistatic.com/hub/i/r/2018/02/04/29e49d64-a291-47b2-ad22-652ae6716ef9/thumbnail/620x398g2/ca4631d41fc14c8475f7ff3f1037ee1e/010918-heavy.jpg"));
        latest.add(new Models("SpaceX is about to make history by relaunching a used Falcon 9 rocket", "Cape Canaveral, FL. July 6, 2020", "https://cbsnews2.cbsistatic.com/hub/i/r/2018/02/04/29e49d64-a291-47b2-ad22-652ae6716ef9/thumbnail/620x398g2/ca4631d41fc14c8475f7ff3f1037ee1e/010918-heavy.jpg"));

        return latest;
    }

    @Override
    public void headerNewsItemListener(int position){
        Toast.makeText(getContext(), "header news item position: " + position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hotNewsItemListener(int position){
        Toast.makeText(getContext(), "hot news item position: " + position, Toast.LENGTH_LONG).show();
    }
}
