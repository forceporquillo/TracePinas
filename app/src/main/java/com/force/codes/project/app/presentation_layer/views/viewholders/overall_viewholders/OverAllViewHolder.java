/*
 * Created by Force Porquillo on 7/17/20 3:41 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/17/20 3:41 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewholders.overall_viewholders;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.CardViewLayoutBinding;
import com.force.codes.project.app.presentation_layer.controller.utils.charts.ChartHelperUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;

public class OverAllViewHolder extends BaseBinderViewHolder {

  private final CardViewLayoutBinding binding;
  private final ChartHelperUtil util;
  private LineChart chart;

  public OverAllViewHolder(@NonNull CardViewLayoutBinding binding, ChartHelperUtil util) {
    super(binding.getRoot());
    this.binding = binding;
    this.util = util;
  }

  public CardViewLayoutBinding getBinding() {
    return binding;
  }

  public void setLineChart() {
    chart = binding.lineChart;
    // we want our chart to be clean as possible.
    {
      chart.setBackgroundColor(Color.WHITE);
      chart.setScaleEnabled(false);
      chart.getDescription().setEnabled(false);
      chart.setTouchEnabled(false);
      chart.setDrawGridBackground(false);
      chart.getAxisRight().setEnabled(false);
      chart.getDescription().setEnabled(false);
      chart.getLegend().setEnabled(false);
      chart.animateX(1000);
    }

    XAxis xAxis;
    {
      xAxis = chart.getXAxis();
      xAxis.setAxisMinimum(0f);
      xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
      xAxis.setLabelRotationAngle(-45);
    }

    YAxis yAxis;
    {
      yAxis = chart.getAxisLeft();
      yAxis.setAxisMinimum(0.0f);
    }

    setData(45, 180);
  }

  private void setData(int count, float range) {
    final Drawable drawable = ContextCompat.getDrawable(chart.getContext(), R.drawable.fade_red);

    ArrayList<Entry> values = new ArrayList<>();

    for (int i = 0; i < count; ++i) {
      values.add(new Entry(i * 5, 100 * (i * 5)));
    }

    LineDataSet dataSet = new LineDataSet(values, "");
    dataSet.setDrawFilled(true);
    dataSet.setFillDrawable(drawable);
    dataSet.setColor(ContextCompat.getColor(chart.getContext(), R.color.blue));
    dataSet.setCircleRadius(4f);
    dataSet.setCircleColor(ContextCompat.getColor(chart.getContext(), R.color.blue));
    LineData data = new LineData(dataSet);
    data.setDrawValues(false);
    chart.setData(data);
    chart.invalidate();
  }
}
