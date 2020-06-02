package com.force.codes.project.covid19.controller.components;

/*
 * Created by Force Porquillo on 6/2/20 1:49 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 12:55 PM
 *
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.force.codes.project.covid19.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountryStatisticsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountryStatisticsFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CountryStatisticsFragment(){
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CountryStatisticsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CountryStatisticsFragment newInstance(String param1, String param2){
        CountryStatisticsFragment fragment = new CountryStatisticsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_statistics, container, false);
    }
}
