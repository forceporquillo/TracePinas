package com.force.codes.project.app.presentation_layer.views.fragments.mapview;

/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:24 AM
 *
 */

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
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
import com.force.codes.project.app.presentation_layer.views.adapters.CustomInfoWindowMap;
import com.force.codes.project.app.presentation_layer.views.fragments.BaseFragment;
import com.force.codes.project.app.service.executors.AppExecutors;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;


public class MapFragment extends BaseFragment implements
        LocationSource.OnLocationChangedListener,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback, GoogleMap.OnMarkerClickListener, ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnMapClickListener{

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @BindView(R.id.map_view)
    MapView mapView;

    private GoogleMap map;
    private Unbinder unbinder;
    private View view;
    private MapViewModel mapViewModel;
    private boolean permissionDenied = false;
    private boolean isMapReady;
    private AppExecutors executors;
    private CustomInfoWindowMap infoWindowMap;

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
            isMapReady = googleMap.setMapStyle(MapStyleOptions
                    .loadRawResourceStyle(view.getContext(),
                            R.raw.map_style_milk));

            if(isMapReady){
                enableLocation();
            }

        } catch(Resources.NotFoundException e){
            Timber.e(e);
        }

        LatLng latLng = new LatLng(12.8797, 121.7740);

        googleMap.setMinZoomPreference(2);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 6));

        googleMap.setOnMyLocationButtonClickListener(this);
        googleMap.setOnMyLocationClickListener(this);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onStart(){
        super.onStart();
        mapView.onStart();
        mapViewModel.getLocalLiveData().observe(this, listData -> {
            // decouples necessary data to reduce time complexity.
            final ArrayList<LatLng> latLang = new ArrayList<>();
            final Map<LatLng, Integer> mapOfCases = new HashMap<>();
            final Map<LatLng, String> location = new HashMap<>();
            final ArrayList<String> address = new ArrayList<>();

            executors.computationalThread().execute(() -> {
                listData.getData().forEach(phDataSet -> {
                    // region ...
                    String stringLat = phDataSet.getLatitude();
                    String stringLng = phDataSet.getLongitude();
                    assert stringLat != null;
                    assert stringLng != null;
                    // endregion
                    if(!stringLat.isEmpty() && !stringLng.isEmpty()){
                        latLang.add(new LatLng(
                                Double.parseDouble(stringLat),
                                Double.parseDouble(stringLng))
                        );

                        address.add(phDataSet.getLocation());
                    }
                });

                latLang.forEach(latLng -> {
                    try{
                        if(mapOfCases.containsKey(latLng)){
                            mapOfCases.put(latLng, mapOfCases.get(latLng) + 1);
                        } else{
                            mapOfCases.put(latLng, 1);
                        }
                    } catch(NullPointerException e){
                        e.printStackTrace();
                    }
                });

                if(!permissionDenied){
                    executors.mainHandler().execute(() -> {
                        if(isMapReady){
                            map.setInfoWindowAdapter(infoWindowMap =
                                    new CustomInfoWindowMap(view.getContext(), mapOfCases)
                            );

                            mapOfCases.forEach((latLng, cases) -> {
                                Marker marker = map.addMarker(
                                        getCircleMarkers(latLng));
                                marker.setTag(mapOfCases);
                            });
                        }
                    });
                }
            });
        });
    }

    @NotNull
    private BitmapDescriptor bitmapDescriptorFromVector(Context context){
        Drawable vectorDrawable = ContextCompat.getDrawable(context, R.drawable.circle_marker);
        assert vectorDrawable != null;
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @NotNull
    private MarkerOptions getCircleMarkers(@NotNull LatLng gc){
        return new MarkerOptions().
                position(new LatLng(gc.latitude, gc.longitude)).
                icon(bitmapDescriptorFromVector(view.getContext()));
    }

    private void enableLocation(){
        if(ContextCompat.checkSelfPermission(view.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
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
    public void onMapClick(LatLng latLng){
        Toast.makeText(view.getContext(), "Henlo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location){
        //
    }

    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();

        if(isMapReady){
            Toast.makeText(view.getContext(), "Map is ready...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onMyLocationButtonClick(){
        if(permissionDenied){
            return true;
        }
        executors.computationalThread().execute(() -> {
            Geocoder convertCoordinate = new Geocoder(view.getContext(), Locale.getDefault());
            try{
                List<Address> address = convertCoordinate.getFromLocation(11.5810, 122.1178, 1);
                executors.mainHandler().execute(() ->
                        Toast.makeText(view.getContext(), address.get(0).getLocality(), Toast.LENGTH_LONG).show());
            } catch(IOException e){
                e.printStackTrace();
            }
        });
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
        MapViewModelFactory modelFactory = Injection.
                providesMapViewModelFactory();
        mapViewModel = new ViewModelProvider(this, modelFactory).
                get(MapViewModel.class);
        mapViewModel.getDataFromNetwork();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mapView.onDestroy();

        if(infoWindowMap.getContext() != null){
            infoWindowMap.clearContext();
        }

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
