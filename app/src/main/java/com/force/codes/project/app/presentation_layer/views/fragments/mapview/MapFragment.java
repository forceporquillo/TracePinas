package com.force.codes.project.app.presentation_layer.views.fragments.mapview;

/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:24 AM
 *
 */

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.force.codes.project.app.R;
import com.force.codes.project.app.app.Injection;
import com.force.codes.project.app.factory.MapViewModelFactory;
import com.force.codes.project.app.presentation_layer.views.fragments.BaseFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;


public class MapFragment extends BaseFragment implements OnMapReadyCallback{

    @BindView(R.id.map_view)
    MapView mapView;

    private GoogleMap googleMap;

    private Unbinder unbinder;
    private View view;

    MapViewModel mapViewModel;

    public MapFragment(){
        // Required empty public constructor
    }

    public static MapFragment newInstance(){
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        this.googleMap = googleMap;

        try{
            googleMap.setMapStyle(MapStyleOptions
                    .loadRawResourceStyle(view.getContext(), R.raw.map_style_milk));
        } catch(Resources.NotFoundException e){
            Timber.e(e);
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        mapView.onStart();

        mapViewModel.getLiveData().observe(this, listData -> {
            if(listData.getData().size() == 0){
                new Handler().postDelayed(() -> {
                    for(int i = 0; i < listData.getData().size(); ++i){

                        String latitude = listData.getData().get(i).getLatitude();
                        String longitude = listData.getData().get(i).getLongitude();

                        assert longitude != null;
                        assert latitude != null;

                        if(!latitude.equals("") && !longitude.equals("")){
                            double lat = Double.parseDouble(latitude);
                            double lng = Double.parseDouble(longitude);

                            LatLng latLng = new LatLng(lat, lng);
                            LatLng ph = new LatLng(16.566233, 121.262634);

                            googleMap.moveCamera(CameraUpdateFactory
                                    .newCameraPosition(new CameraPosition(ph, 1, 0, 0))
                            );

                            googleMap.addCircle(new CircleOptions()
                                    .center(latLng)
                                    .radius(1000)
                                    .strokeWidth(1)
                                    .strokeColor(Color.RED)
                                    .fillColor(Color.RED)); // transparent
                        }
                    }
                }, 50);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_map, container, false);
        unbinder = ButterKnife.bind(this, view);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        try{
            MapsInitializer.initialize(view.getContext());
        } catch(Exception e){
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        MapViewModelFactory modelFactory = Injection
                .providesMapViewModelFactory();
        mapViewModel = new ViewModelProvider(this, modelFactory)
                .get(MapViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mapView.onDestroy();

        if(mapView != null){
            mapView = null;
        }

        if(googleMap != null){
            googleMap = null;
        }

        unbinder.unbind();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onStop(){
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }
}
