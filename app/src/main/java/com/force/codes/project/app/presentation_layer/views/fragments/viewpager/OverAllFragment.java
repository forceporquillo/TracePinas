/*
 * Created by Force Porquillo on 7/17/20 3:29 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/17/20 3:29 AM
 */

package com.force.codes.project.app.presentation_layer.views.fragments.viewpager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.views.adapters.OverAllAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OverAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OverAllFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OverAllFragment(){
        // Required empty public constructor
    }

    @BindView(R.id.over_all_recyclerview)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private OverAllAdapter allAdapter;

    public static OverAllFragment newInstance(String param1, String param2){
        OverAllFragment fragment = new OverAllFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Timber.e("ON CREATE");
        if(getArguments() != null){
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        allAdapter = new OverAllAdapter();
        setRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        Timber.e("ON CREATE VIEW");
        View view = inflater.inflate(R.layout.fragment_over_all, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        Timber.e("ON START");
        recyclerView.setAdapter(allAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        Timber.e("ON RESUME");
    }

    @Override
    public void onPause(){
        super.onPause();
        Timber.e("ON PAUSE");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Timber.e("ON DESTROY");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
        allAdapter = null;
        Timber.e("ON DESTROY VIEW");
    }

    private void setRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        Timber.e("INIT RECYCLERVIEW");
    }
}