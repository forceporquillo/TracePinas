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
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.force.codes.project.app.R;
import com.force.codes.project.app.app.Injection;
import com.force.codes.project.app.data_layer.model.GlobalData;
import com.force.codes.project.app.factory.MapViewModelFactory;
import com.force.codes.project.app.presentation_layer.views.adapters.CustomInfoWindowAdapter;
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

import org.angmarch.views.NiceSpinner;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
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

    private static final int BUILD_VERSION = Build.VERSION.SDK_INT;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final String GLOBAL_CASES = "Global Cases";
    private static final String PHILIPPINES = "Philippines";

    @BindView(R.id.map_view)
    MapView mapView;

    @BindView(R.id.nice_spinner)
    NiceSpinner niceSpinner;

    private GoogleMap map;
    private Unbinder unbinder;
    private View view;
    private MapViewModel mapViewModel;
    private boolean permissionDenied = false;
    private boolean isMapReady;
    private AppExecutors executors;
    private List<String> dataSet;

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
                    .loadRawResourceStyle(view.getContext(), R.raw.map_style_milk));
            if(isMapReady)
                enableMyLocation();
        } catch(Resources.NotFoundException e){
            Timber.e(e);
        }

        LatLng latLng = new LatLng(12.8797, 121.7740);
        googleMap.setMinZoomPreference(2);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7));
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
        getLocalLiveData();
        setSpinner();
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
    private MarkerOptions getMarkerOptions(@NotNull LatLng gc, int cases){
        return new MarkerOptions()
                .position(new LatLng(
                        gc.latitude, gc.longitude))
                .icon(bitmapDescriptorFromVector(view.getContext()));
    }

    private void enableMyLocation(){
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

        if(requestCode != LOCATION_PERMISSION_REQUEST_CODE){
            permissionDenied = true;
            return;
        }

        enableMyLocation();
    }

    @TestOnly
    @Override
    public boolean onMyLocationButtonClick(){
        if(permissionDenied){
            return true;
        }
        executors.computationIO().execute(() -> {
            Geocoder convertCoordinate = new Geocoder(view.getContext(), Locale.getDefault());
            try{
                List<Address> address = convertCoordinate.getFromLocation(11.5810, 122.1178, 1);
                executors.mainThread().execute(() ->
                        Toast.makeText(view.getContext(), address.get(0).getLocality(), Toast.LENGTH_LONG).show());
            } catch(IOException e){
                e.printStackTrace();
            }
        });
        return false;
    }

    @Override
    public void onMapClick(LatLng latLng){
        Toast.makeText(view.getContext(), "Henlo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location){
        //
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
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
            Timber.e(e);
        }

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setSpinner(){
        niceSpinner.attachDataSource(dataSet);
        niceSpinner.setOnSpinnerItemSelectedListener((parent, view, position, id) -> {
            String atPosition = (String) parent.getItemAtPosition(position);
            switch(atPosition){
                case PHILIPPINES:
                    map.clear();
                    getLocalLiveData();
                    break;
                case GLOBAL_CASES:
                    map.clear();
                    getGlobalLiveData();
                    break;
            }
        });
    }

    private static boolean isEmpty(@NotNull String lat, String lng){
        return lat.isEmpty() && lng.isEmpty();
    }

    @NotNull
    @Contract("_, _ -> new")
    private static LatLng coordinate(String lat, String lng){
        return new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getLocalLiveData(){
        mapViewModel.getLocalLiveData().observe(getViewLifecycleOwner(), listData -> {
            final ArrayList<LatLng> latLang = new ArrayList<>();
            final Map<LatLng, Integer> mapOfCases = new HashMap<>();

            executors.computationIO().execute(() -> {
                listData.getData().forEach(phDataSet -> {
                    final String lat = phDataSet.getLatitude();
                    final String lng = phDataSet.getLongitude();

                    assert lat != null;
                    assert lng != null;

                    if(!isEmpty(lat, lng)){
                        latLang.add(coordinate(lat, lng));
                    }
                });

                latLang.forEach(latLng -> {
                    Integer numCases = mapOfCases.get(latLng);
                    mapOfCases.put(latLng, (numCases == null) ? 1 : numCases + 1);
                });

                executors.mainThread().execute(() -> {
                    if(isMapReady){
                        map.setInfoWindowAdapter(new CustomInfoWindowAdapter(view.getContext()));
                        final Marker[] marker = new Marker[1];
                        mapOfCases.forEach((latLng, numCases) -> {
                            marker[0] = map.addMarker(getMarkerOptions(latLng, numCases));
                            marker[0].setSnippet(numCases.toString());
                            marker[0].setTag(latLng);
                        });
                    }
                });
            });
        });
    }

    private void getGlobalLiveData(){
        mapViewModel.getGlobalLiveData().observe(getViewLifecycleOwner(), globalData ->
                executors.computationIO().execute(() -> {
                    for(GlobalData gd : globalData){
                        executors.mainThread().execute(() -> {
                            Marker marker = map.addMarker(getMarkerOptions(new LatLng(
                                    gd.getLatitude(), gd.getLongitude()), gd.getActive()));
                            marker.setTitle(gd.getCombinedKey());
                            marker.setSnippet(String.valueOf(gd.getConfirmed()));
                        });
                    }
                })
        );
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // enable gpu rendering on lower devices.
        // target device N and below
        if(BUILD_VERSION <= Build.VERSION_CODES.N){
            if(getActivity() == null){
                return;
            }

            Timber.i("Hardware acceleration turned on");
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        }

        dataSet = new LinkedList<>(Arrays.asList(PHILIPPINES, GLOBAL_CASES));
        executors = new AppExecutors();
        MapViewModelFactory modelFactory = Injection.providesMapViewModelFactory();
        mapViewModel = new ViewModelProvider(this, modelFactory).get(MapViewModel.class);
        mapViewModel.getLocalDataFromNetwork();
        mapViewModel.getGlobalDataFromNetwork();
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

        view = null;
        unbinder.unbind();

        if(BUILD_VERSION <= Build.VERSION_CODES.N){
            if(getActivity() != null){
                Timber.e("Hardware acceleration turned off");
                getActivity().getWindow().clearFlags(
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        }
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
