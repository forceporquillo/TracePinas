/*
 * Created by Force Porquillo on 6/2/20 8:55 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/29/20 8:54 PM
 */

package com.force.codes.project.app.presentation_layer.views.fragments;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.map_data.PHDataSet;
import com.force.codes.project.app.databinding.FragmentMapBinding;
import com.force.codes.project.app.presentation_layer.controller.utils.AppExecutors;
import com.force.codes.project.app.presentation_layer.controller.utils.Utils;
import com.force.codes.project.app.presentation_layer.views.adapters.CustomInfoWindowAdapter;
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory;
import com.force.codes.project.app.presentation_layer.views.viewmodels.MapViewModel;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import org.angmarch.views.NiceSpinner;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class MapFragment extends BaseFragment implements
    GoogleMap.OnMyLocationButtonClickListener,
    OnMapReadyCallback,
    ActivityCompat.OnRequestPermissionsResultCallback {

  private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
  private static final String GLOBAL_CASES = "Global Cases";
  private static final String PHILIPPINES = "Philippines";
  private static final int DELAY = 0;
  @Inject
  Runnable[] runnable;
  @Inject
  Marker[] marker;
  @Inject
  ViewModelProviderFactory factory;
  @Inject
  AppExecutors executors;
  @Inject
  @Named("MapListDataSet")
  List<String> dataSet;
  private boolean permissionDenied = false;
  private boolean isHardwareEnabled = false;
  private boolean isMapReady;
  private GoogleMap map;
  private MapViewModel mapViewModel;
  private FragmentMapBinding binding;

  public MapFragment() {

  }

  public static MapFragment newInstance() {
    MapFragment fragment = new MapFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  private static int computeRadiusPerCases(int cases) {
    if (cases / 5000 == 0 || cases / 5000 < 0) {
      return 5;
    } else if (cases <= 1000 && cases != 0) {
      return 10;
    }

    int temp = cases / 5000;

    if (temp <= 0) {
      return 4;
    }

    return temp + 2;
  }

  @NotNull
  private static LatLng coordinate(String lat, String lng) {
    return new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
  }

  private static boolean checkIfEmpty(@NotNull String[] coordinates) {
    return coordinates[0].isEmpty() && coordinates[1].isEmpty();
  }

  private static boolean isEmpty(double latitude, double longitude) {
    return latitude == 0.0 && longitude == 0.0;
  }

  static void setDelayAnimation(Runnable runnable) {
    new Handler().postDelayed(runnable, DELAY);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  private static Bitmap getBitmapFromVector(Context context, int cases) {
    Drawable drawable = ContextCompat.getDrawable(context, R.drawable.circle_marker);
    assert drawable != null;
    final int radiusPerCases = computeRadiusPerCases(cases);
    Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth() * radiusPerCases,
        drawable.getIntrinsicHeight() * radiusPerCases, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
    drawable.draw(canvas);
    return bitmap;
  }

  private CameraUpdate setCameraPosition(String snippet) {
    final LatLng latLng = new LatLng(12.8797, 121.7740);
    int zoomVelocity;

    if (snippet.equals(PHILIPPINES)) {
      zoomVelocity = 7;
    } else {
      zoomVelocity = 4;
    }

    return CameraUpdateFactory.newLatLngZoom(latLng, zoomVelocity);
  }

  @Override public void onMapReady(GoogleMap googleMap) {
    map = googleMap;

    assert getContext() != null;
    isMapReady = googleMap.setMapStyle(MapStyleOptions
        .loadRawResourceStyle(getContext(), R.raw.map_style_milk));

    if (isMapReady) {
      enableMyLocation();
      map.setInfoWindowAdapter(new CustomInfoWindowAdapter(getContext()));
    }

    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(12.8797, 121.7740), 7));
    googleMap.setOnMyLocationButtonClickListener(this);
  }

  @Override public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentMapBinding.inflate(inflater, container, false);
    binding.setMap(this);
    binding.setLifecycleOwner(this);
    binding.setVariable(BR.map, this);

    binding.mapView.onCreate(savedInstanceState);

    if (savedInstanceState == null) {
      binding.mapView.getMapAsync(this);

      try {
        MapsInitializer.initialize(binding.getRoot().getContext());
      } catch (Exception e) {
        Timber.e(e);
      }
    }
    return binding.getRoot();
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (binding.hasPendingBindings()) {
      binding.executePendingBindings();
      binding.invalidateAll();
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
      permissionDenied = true;
      return;
    }
    enableMyLocation();
  }

  @Override public boolean onMyLocationButtonClick() {
    if (permissionDenied) {
      return true;
    }
    executors.computationIO().execute(() -> {
      Geocoder convertCoordinate =
          new Geocoder(binding.getRoot().getContext(), Locale.getDefault());
      try {
        List<Address> address = convertCoordinate.getFromLocation(11.5810, 122.1178, 1);
        executors.mainThread().execute(() ->
            Toast.makeText(binding.getRoot().getContext(), address.get(0).getLocality(),
                Toast.LENGTH_LONG)
                .show());
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    return false;
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  private void setSpinner() {
    final NiceSpinner niceSpinner = binding.niceSpinner;
    niceSpinner.attachDataSource(dataSet);
    niceSpinner.setOnSpinnerItemSelectedListener((parent, view, position, id) -> {
      String atPosition = (String) parent.getItemAtPosition(position);
      switch (atPosition) {
        case PHILIPPINES:
          map.clear();
          map.animateCamera(setCameraPosition(PHILIPPINES));
          runnable[0] = () -> getLocalLiveData(marker);
          setDelayAnimation(runnable[0]);
          break;
        case GLOBAL_CASES:
          map.clear();
          map.animateCamera(setCameraPosition(GLOBAL_CASES));
          runnable[0] = () -> getGlobalLiveData(marker);
          setDelayAnimation(runnable[0]);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + atPosition);
      }
    });
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  private void getLocalLiveData(final Marker[] marker) {
    mapViewModel.getMutablePhData().observe(this, listData -> {
      final ArrayList<LatLng> latLang = new ArrayList<>();
      final Map<LatLng, Integer> mapOfCases = new HashMap<>();

      executors.computationIO().execute(() -> {
        for (PHDataSet dataSet : listData.getData()) {
          final String[] lat = new String[] {
              dataSet.getLatitude(),
              dataSet.getLongitude()
          };
          if (!checkIfEmpty(lat)) {
            latLang.add(coordinate(lat[0], lat[1]));
          }
        }

        for (LatLng lt : latLang) {
          Integer cases = mapOfCases.get(lt);
          mapOfCases.put(lt, (cases == null) ? 1 : cases + 1);
        }

        executors.mainThread().execute(() -> {
          if (isMapReady) {
            for (Map.Entry<LatLng, Integer> entry : mapOfCases.entrySet()) {
              marker[0] = map.addMarker(getMarkerOptions(
                  entry.getKey(), entry.getValue())
              );
              marker[0].setSnippet(entry.getValue().toString());
              marker[0].setTag(entry.getKey());
            }
          }
        });
      });
    });
  }

  private void enableMyLocation() {
    if (ContextCompat.checkSelfPermission(binding.getRoot().getContext(),
        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
      if (map != null) {
        map.setMyLocationEnabled(true);
      }
    } else {
      requestPermissions(new String[] {
          Manifest.permission.ACCESS_COARSE_LOCATION,
          android.Manifest.permission.ACCESS_FINE_LOCATION
      }, LOCATION_PERMISSION_REQUEST_CODE);
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    assert getActivity() != null;

    if (savedInstanceState == null) {
      if (Utils.getSDKInt() <= Build.VERSION_CODES.N) {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        isHardwareEnabled = true;
      }

      mapViewModel = new ViewModelProvider(this, factory).get(MapViewModel.class);
      mapViewModel.getListGlobalData();
      mapViewModel.getAllPhData();
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  private void getGlobalLiveData(Marker[] marker) {
    mapViewModel.getMutableGlobalData().observe(this, data ->
        data.forEach(gd -> {
          if (!isEmpty(gd.getLatitude(), gd.getLongitude())) {
            marker[0] = map.addMarker(getMarkerOptions(new LatLng(
                gd.getLatitude(), gd.getLongitude()), gd.getConfirmed()));
            marker[0].setTitle(gd.getCombinedKey());
            marker[0].setSnippet(String.valueOf(gd.getConfirmed()));
          }
        }));
  }

  @NotNull private MarkerOptions getMarkerOptions(@NotNull LatLng gc, int cases) {
    return new MarkerOptions()
        .position(new LatLng(
            gc.latitude,
            gc.longitude)
        )
        .alpha(0.7f)
        .flat(true)
        .icon(BitmapDescriptorFactory
            .fromBitmap(getBitmapFromVector(getContext(), cases)));
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  @Override public void onStart() {
    super.onStart();
    binding.mapView.onStart();
    getLocalLiveData(marker);
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  @Override public void onResume() {
    super.onResume();
    binding.mapView.onResume();
    setSpinner();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding.mapView.onDestroy();
    map = null;
    marker = null;

    if (isHardwareEnabled) {
      if (getActivity() == null) {
        return;
      }
      Timber.e("Hardware acceleration turned off");
      getActivity().getWindow().clearFlags(
          WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
      isHardwareEnabled = false;
    }
  }

  @Override public void onStop() {
    super.onStop();
    binding.mapView.onStop();
  }

  @Override public void onLowMemory() {
    super.onLowMemory();
    binding.mapView.onLowMemory();
  }

  @Override public void onPause() {
    super.onPause();
    binding.mapView.onPause();
  }

  @Override public void onNetworkConnectionChanged(Connectivity connectivity) {

  }

  @Override public void onInternetConnectionChanged(Boolean isConnected) {

  }
}