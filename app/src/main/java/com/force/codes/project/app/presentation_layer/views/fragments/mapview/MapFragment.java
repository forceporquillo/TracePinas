package com.force.codes.project.app.presentation_layer.views.fragments.mapview;

/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:24 AM
 *
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.force.codes.project.app.R;
import com.force.codes.project.app.app.Injection;
import com.force.codes.project.app.factory.MapViewModelFactory;
import com.force.codes.project.app.presentation_layer.views.fragments.BaseFragment;
import com.force.codes.project.app.service.executors.AppExecutors;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;


public class MapFragment extends BaseFragment implements
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback, GoogleMap.OnMarkerClickListener, ActivityCompat.OnRequestPermissionsResultCallback{

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    @BindView(R.id.map_view)
    MapView mapView;
    private GoogleMap map;
    private Unbinder unbinder;
    private View view;
    private MapViewModel mapViewModel;
    private boolean permissionDenied = false;
    private boolean success;
    private AppExecutors executors;

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
        map = googleMap;

        try{
            success = googleMap.setMapStyle(MapStyleOptions
                    .loadRawResourceStyle(view.getContext(),
                            R.raw.map_style_milk));
        } catch(Resources.NotFoundException e){
            Timber.e(e);
        }

        LatLng latLng = new LatLng(12.8797, 121.7740);

        googleMap.setMinZoomPreference(2);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 6));

        googleMap.setOnMyLocationButtonClickListener(this);
        googleMap.setOnMyLocationClickListener(this);

        enableLocation();
    }

    @Override
    public boolean onMarkerClick(Marker marker){
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .zoom(10)
                .build();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate);

        return true;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location){

    }

    @Override
    public void onResume(){
        super.onResume();
        if(permissionDenied){
            //TODO: add dialogue fragment.
            permissionDenied = false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onStart(){
        super.onStart();
        mapView.onStart();
        mapViewModel.getLocalLiveData().observe(this, listData -> {
            if(listData.getData().size() == 0){
                // if no data present in DB fetch from network api.
                mapViewModel.getDataFromNetwork();
            } else{
                // reducing time complexity by decoupling necessary
                // data since our listData is nested list of objects.
                final ArrayList<LatLng> latLang = new ArrayList<>();
                final Map<LatLng, Integer> mapOfCases = new HashMap<>();

                // check if data is verified and not null. if not,
                // iterate each list of geo. coordinates, parse
                // string as double and add to arrayList.
                // Run data computation on separate custom thread.
                executors.computationalThread().execute(() -> {
                    listData.getData().forEach(phDataSet -> {
                        String stringLat = phDataSet.getLatitude();
                        String stringLng = phDataSet.getLongitude();

                        assert stringLat != null;
                        assert stringLng != null;

                        if(!stringLat.equals("") && !stringLng.equals("")){
                            double lat = Double.parseDouble(stringLat);
                            double lang = Double.parseDouble(stringLng);
                            LatLng latLng = new LatLng(lat, lang);
                            latLang.add(latLng);
                        }
                    });

                    // TODO: reduce time complexity since we have 32,000+ data.
                    // we only need 1 coordinate representative in each location.
                    // finding duplicate coordinates using hashMap and instead of
                    // iterating each list and adding to map where complexity is O(k+logN),
                    // thus this results in app unresponsive. So, we linearly scan and
                    // find each case with same coordinate and respectively adds these
                    // cases in a particular coordinate as cases counts.
                    latLang.forEach(latLng -> {
                        if(mapOfCases.containsKey(latLng)){
                            mapOfCases.put(latLng, mapOfCases.get(latLng) + 1);
                        } else{
                            mapOfCases.put(latLng, 1);
                        }
                    });

                    if(getActivity() == null)
                        return;

                    if(!permissionDenied){
                        // update UI thread
                        // iterating each coordinates and adding circular cluster
                        // marker to google map together with numCases as popup title.
                        getActivity().runOnUiThread(() -> {
                            if(success) new Handler().postDelayed(() -> mapOfCases
                                    .forEach((latLng, cases) -> addCircles(latLng)), 150);
                        });
                    }
                });
            }
        });
    }

    private void addCircles(LatLng gc){
        map.addCircle(new CircleOptions()
                .center(new LatLng(gc.latitude, gc.longitude))
                .radius(1500)
                .strokeWidth(2)
                .strokeColor(Color.RED)
                .fillColor(Color.argb(50, 255, 3, 3)));

    }

    private void enableLocation(){
        if(ContextCompat.checkSelfPermission(view.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            if(map != null){
                map.setMyLocationEnabled(true);
            }
        } else{
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
            }, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE){
            enableLocation();
        } else{
            permissionDenied = true;
        }
    }

    @Override
    public boolean onMyLocationButtonClick(){
        Toast.makeText(view.getContext(), "Current location:", Toast.LENGTH_LONG).show();
        return false;
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
        executors = new AppExecutors();
        MapViewModelFactory modelFactory = Injection
                .providesMapViewModelFactory();
        mapViewModel = new ViewModelProvider(this, modelFactory)
                .get(MapViewModel.class);
        //mapViewModel.getDataFromNetwork();
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

        if(map != null){
            map = null;
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
