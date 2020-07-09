/*
 * Created by Force Porquillo on 7/8/20 1:40 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/8/20 1:40 AM
 */

package com.force.codes.project.app.presentation_layer.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.force.codes.project.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadNewsFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReadNewsFragment(){
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReadNewsFragment.
     */
    // TODO: Rename and change types and number of parameters

    @BindView(R.id.title)
    TextView textView;

    @BindView(R.id.image)
    ImageView imageView;

    private Unbinder unbinder;

    public static ReadNewsFragment newInstance(String param1, String param2){
        ReadNewsFragment fragment = new ReadNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_read_news, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(mParam2)
                .centerCrop()
                .into(imageView);

        textView.setText(mParam1);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        textView = null;
        imageView = null;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}