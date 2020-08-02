/*
 * Created by Force Porquillo on 7/26/20 4:01 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/26/20 4:01 PM
 */

package com.force.codes.project.app.presentation_layer.controller.utils.charts;

public abstract class ChartHelperUtil {

  public ChartHelperUtil() {

  }

  //    public static void setLineChart(LineChart chart){
  //        chart.setBackgroundColor(Color.WHITE);
  //        chart.setScaleEnabled(false);
  //        chart.getDescription().setEnabled(false);
  //        chart.setTouchEnabled(false);
  //        chart.setDrawGridBackground(false);
  //        chart.getAxisRight().setEnabled(false);
  //        chart.getDescription().setEnabled(false);
  //        chart.getLegend().setEnabled(false);
  //        chart.animateX(1000);
  //
  //        setData(chart, 45, 180);
  //    }
  //
  //    private static void setData(LineChart chart, int ii, int i1){
  //        final Drawable drawable = ContextCompat.getDrawable(chart.getContext(), R.drawable.fade_red);
  //
  //        ArrayList<Entry> values = new ArrayList<>();
  //
  //        for(int i = 0; i < ii; ++i){
  //            values.add(new Entry(i * 5, 100 * (i * 5)));
  //        }
  //
  //        LineDataSet dataSet = new LineDataSet(values, "");
  //        dataSet.setDrawFilled(true);
  //        dataSet.setFillDrawable(drawable);
  //        dataSet.setColor(ContextCompat.getColor(chart.getContext(), R.color.blue));
  //        dataSet.setCircleRadius(4f);
  //
  //        dataSet.setCircleColor(ContextCompat.getColor(chart.getContext(), R.color.blue));
  //
  //        LineData data = new LineData(dataSet);
  //        data.setDrawValues(false);
  //        chart.setData(data);
  //        chart.invalidate();
  //    }
}
