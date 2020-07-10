/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:01 AM
 */

package com.force.codes.project.app.presentation_layer.views.fragments;

/*
 * Created by Force Porquillo on 6/4/20 9:30 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 6:19 AM
 *
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.force.codes.project.app.R;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticsFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //@BindView(R.id.viewpager)
    //ViewPager viewPager;

    //@BindView(R.id.tablayout)
    //SmartTabLayout smartTabLayout;

    @BindView(R.id.share_button)
    ImageButton shareButton;

    private FragmentPagerItemAdapter adapter;

    public StatisticsFragment(){
        // Required empty public constructor
    }

    public static StatisticsFragment newInstance(){
        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

//        adapter = new FragmentPagerItemAdapter(getChildFragmentManager(),
//                FragmentPagerItems.with(getContext())
//                        .add("GLOBAL", Fragment1.class)
//                        .add("MY COUNTRY", Fragment2.class)
//                        .add("LIST", Fragment3.class)
//                        .create()
//        );

        if(getArguments() != null){
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_country_statistics, container, false);
        ButterKnife.bind(this, view);

        shareButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(), "Henlo", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
//        viewPager.setAdapter(adapter);
//        smartTabLayout.setViewPager(viewPager);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        shareButton = null;
    }
}
