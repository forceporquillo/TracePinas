/*
 * Created by Force Porquillo on 7/17/20 2:16 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/17/20 2:16 PM
 */

package com.force.codes.project.app.presentation_layer.views.fragments.viewpager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.BuildConfig;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.data_layer.model.philippines.Philippines;
import com.force.codes.project.app.databinding.FragmentMyCountryBinding;
import com.force.codes.project.app.presentation_layer.controller.service.ThreadExecutor;
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener;
import com.force.codes.project.app.presentation_layer.controller.utils.Utils;
import com.force.codes.project.app.presentation_layer.views.activity.ListViewActivity;
import com.force.codes.project.app.presentation_layer.views.adapters.TopRegionsAdapter;
import com.force.codes.project.app.presentation_layer.views.base.BaseFragment;
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory;
import com.force.codes.project.app.presentation_layer.views.viewmodels.MyCountryViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCountryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class MyCountryFragment extends BaseFragment
    implements StackEventListener.ListActivityListener, StackEventListener.ViewTopRegionsListener {

  private static final String ARGS_KEY = "country";

  private static final String DEFAULT_ENDPOINT = "Philippines";

  private static final int MARGIN_WIDTH = 10;

  private static final int GRID_COLUMNS = 2;

  private FragmentMyCountryBinding binding;

  private MyCountryViewModel viewModel;

  private String getArgsKey = null;

  private static final ObservableBoolean IS_CONNECTED = new ObservableBoolean();

  public MyCountryFragment() {
    // Required empty public constructor
  }

  @Inject ViewModelProviderFactory factory;

  public static MyCountryFragment newInstance() {
    MyCountryFragment fragment = new MyCountryFragment();
    Bundle args = new Bundle();
    args.putString(ARGS_KEY, "");
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      viewModel = new ViewModelProvider(this, factory).get(MyCountryViewModel.class);
    }
  }

  @Override public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentMyCountryBinding.inflate(inflater, container, false);
    binding.setCountryViewModel(viewModel);
    binding.setLifecycleOwner(this);
    binding.setListCallback(this);
    binding.setListener(this);
    renderGridBoxAtRuntime(container.getLayoutParams());
    return binding.getRoot();
  }

  @Override public void onStart() {
    super.onStart();
    getArgsKey = getArgsKey == null ? DEFAULT_ENDPOINT : getArgsKey;
    viewModel.getPrimarySelected().observe(this, country ->
        Timber.i("LiveData auto update UI emits: %s", getArgsKey = country)
    );
    new ThreadExecutor(100).mainThread().execute(() ->
        viewModel.getCountryData(getArgsKey).observe(this, data ->
            setPieChart(data, getArgsKey.equals(DEFAULT_ENDPOINT))
        )
    );

    viewModel.getPhData().observe(this, philippines -> {
      if (getArgsKey.equals(DEFAULT_ENDPOINT)) {
        setGidBoxLineChart(philippines);
      }
    });

    viewModel.getTopRegions().observe(this, topRegions ->
        setTopRegionsRV(new TopRegionsAdapter(topRegions.getData())));
    binding.invalidateAll();
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (binding.hasPendingBindings()) {
      binding.executePendingBindings();
    }
  }

  private void renderGridBoxAtRuntime(ViewGroup.LayoutParams params) {
    if (getContext() == null) {
      throw new NullPointerException();
    }
    final int deviceWidth = Utils.getDeviceWidth(getContext());
    params.resolveLayoutDirection(0);
    for (int i = 0; i < boxLayout(binding).length; ++i) {
      params = boxLayout(binding)[i].getLayoutParams();
      if (i % 2 == 0) {
        params.width = getBoxWidth(deviceWidth, MARGIN_WIDTH * GRID_COLUMNS);
      } else {
        params.width = getBoxWidth(deviceWidth, MARGIN_WIDTH);
      }
      boxLayout(binding)[i].setLayoutParams(params);
    }
  }

  final int getBoxWidth(final int deviceWidth, final int width) {
    return (deviceWidth / GRID_COLUMNS) - Utils.dpToPx(getContext(),
        width, true);
  }

  @Override public void onStartListViewActivity() {
    if (IS_CONNECTED.get()) {
      return;
    }
    startActivity(new Intent(getActivity(), ListViewActivity.class));
  }

  @Override public void onResume() {
    super.onResume();
  }

  private void setTopRegionsRV(TopRegionsAdapter adapter) {
    final RecyclerView rv = binding.topRegionsRv;
    rv.setLayoutManager(new LinearLayoutManager(getContext()));
    //rv.addItemDecoration(new ItemDecoration(rv.getContext(), ItemDecoration.VERTICAL_LIST, 0));
    rv.setAdapter(adapter);
    adapter.notifyDataSetChanged();
  }

  @Override public void onPause() {
    super.onPause();
    if (binding != null) {
      synchronized (this) {
        if (BuildConfig.DEBUG) {
          Timber.d("clearing all binding TextViews");
        }
        for (TextView text : textBindingViews()) {
          text.setText("");
        }
        binding.invalidateAll();
      }
      return;
    }
    throw new NullPointerException();
  }

  private TextView[] textBindingViews() {
    return new TextView[] {
        binding.casesNumber,
        binding.newCases,
        binding.deceaseNumber,
        binding.recoveredNumber,
        binding.deceaseNumber
    };
  }

  private void setGidBoxLineChart(Philippines philippines) {
    final LineChart chart = lineChart(binding.lineChart);
    if (isAxisVisible(chart)) {
      this.disableXYAxis(chart, philippines.getData().size());
    }

    final List<Entry> entryList = new ArrayList<>();

    int yValue = 0;
    for (int i = 0; i < philippines.getData().size(); ++i) {
      yValue += philippines.getData().get(i).getCases();
      entryList.add(new Entry(i, yValue));
    }

    chart.setData(new LineData(lineDataSet(entryList)));
    chart.invalidate();
  }

  private void disableXYAxis(@NotNull LineChart chart, int size) {
    {
      YAxis leftAxis = chart.getAxisLeft();
      leftAxis.setEnabled(false);
      YAxis rightAxis = chart.getAxisRight();
      rightAxis.setEnabled(false);
    }
    {
      XAxis xAxis = chart.getXAxis();
      xAxis.setEnabled(false);
    }
    final Legend legend = chart.getLegend();
    legend.setEnabled(false);
  }

  private static LineChart lineChart(@NotNull LineChart lineChart) {
    lineChart.getAxisLeft().setDrawGridLines(false);
    lineChart.getXAxis().setDrawGridLines(false);
    lineChart.getDescription().setEnabled(false);
    lineChart.setTouchEnabled(false);
    lineChart.setAutoScaleMinMaxEnabled(true);
    if (lineChart.getData() != null) {
      lineChart.getData().setHighlightEnabled(false);
    }
    lineChart.animateX(200);
    return lineChart;
  }

  @NotNull private static LineDataSet lineDataSet(List<Entry> entries) {
    LineDataSet lineDataSet = new LineDataSet(entries, "");
    lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
    lineDataSet.setDrawValues(false);
    lineDataSet.setDrawCircleHole(false);
    lineDataSet.setDrawCircles(false);
    lineDataSet.setDrawHighlightIndicators(false);
    lineDataSet.setLineWidth(2f);
    lineDataSet.setCubicIntensity(0.2f);
    return lineDataSet;
  }

  private void setPieChart(@NotNull final CountryDetails details, boolean animate) {
    final AnimatedPieViewConfig config = new AnimatedPieViewConfig();
    config.strokeWidth(70);
    config.animatePie(animate);
    config.startAngle(-90)
        .addData(new SimplePieInfo(
            details.getCases(),
            Color.rgb(50, 120, 210),
            "Cases")
        )
        .addData(new SimplePieInfo(
            details.getDeaths(),
            Color.rgb(255, 93, 93),
            "Deaths")
        )
        .addData(new SimplePieInfo(
            details.getRecovered(),
            Color.rgb(88, 197, 30),
            "Recovered")
        );

    //binding.circlePie.start(config);
    binding.invalidateAll();
  }

  private static boolean isAxisVisible(@NotNull LineChart chart) {
    return chart.getAxisRight().isEnabled() || chart.getAxisLeft().isEnabled();
  }

  private static RelativeLayout[] boxLayout(@NotNull FragmentMyCountryBinding binding) {
    return new RelativeLayout[] {
        binding.containerDeaths,
        binding.containerInfected,
        binding.containerRecovered,
        binding.containerTested
    };
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    binding.unbind();
    binding = null;
  }

  @Override public void onNetworkConnectionChanged(Connectivity connectivity) {

  }

  @Override
  public void onInternetConnectionChanged(Boolean connected) {
    IS_CONNECTED.set(connected);
  }

  @Override public void onViewAllRegions(View view) {
    final AutoTransition transition = new AutoTransition();
    if (binding.topRegionsRv.getVisibility() == View.GONE) {
      startExpandableAnim(transition);
      binding.topRegionsRv.setVisibility(View.VISIBLE);
      binding.clickableTv.setText(getString(R.string.collapse));
    } else {
      startExpandableAnim(transition);
      binding.topRegionsRv.setVisibility(View.GONE);
      binding.clickableTv.setText(getString(R.string.view_all));
    }
    binding.invalidateAll();
  }

  private void startExpandableAnim(AutoTransition transition) {
    TransitionManager.beginDelayedTransition(binding.recyclerCardContainer, transition);
  }

  @Override public void onRegionSelected(View view) {
    if (!getArgsKey.equals(DEFAULT_ENDPOINT)) {
      materialDialog().show();
      return;
    }
    dialogPlus().show();
  }

  final DialogPlus dialogPlus() {
    assert getContext() != null;
    return DialogPlus.newDialog(getContext())
        .setAdapter(new TopRegionsDialogAdapter(topRegions))
        .setExpanded(true)
        .setHeader(R.layout.dialog_header)
        .setOnCancelListener(this)
        .setOnItemClickListener(this)
        .setGravity(Gravity.CENTER)
        .create();
  }

  @Override public void onCancel(@NotNull DialogPlus dialog) {
    dialog.dismiss();
  }

  @Override
  public void onItemClick(@NotNull DialogPlus dialog, Object item, View view, int position) {
    Toast.makeText(getContext(), "item selected " + topRegions.get(position).getRegion(),
        Toast.LENGTH_SHORT).show();
    dialog.onBackPressed(dialog);
  }

  @NotNull final MaterialDialog materialDialog() {
    assert getActivity() != null;
    return new MaterialDialog.Builder(getActivity())
        .setTitle("Sorry!")
        .setMessage("This feature is only available for Philippines.")
        .setCancelable(false)
        .setAnimation(R.raw.lootie_lady_guitar)
        .setPositiveButton("Set country",
            (dialogInterface, which) -> dialogInterface.dismiss())
        .setNegativeButton("Close",
            (dialogInterface, which) -> dialogInterface.dismiss())
        .build();
  }
}