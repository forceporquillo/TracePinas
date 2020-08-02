/*
 * Created by Force Porquillo on 7/17/20 3:40 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/17/20 3:40 AM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.R;
import com.force.codes.project.app.databinding.CardViewLayoutBinding;
import com.force.codes.project.app.presentation_layer.controller.utils.charts.ChartCallback;
import com.force.codes.project.app.presentation_layer.controller.utils.charts.ChartHelperUtil;
import com.force.codes.project.app.presentation_layer.views.viewholders.overall_viewholders.BaseBinderViewHolder;
import com.force.codes.project.app.presentation_layer.views.viewholders.overall_viewholders.OverAllViewHolder;
import java.util.ArrayList;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class OverAllAdapter extends RecyclerView.Adapter<BaseBinderViewHolder>
    implements ChartCallback {
  private static final int MAX_CARD_NUM = 5;
  private ChartHelperUtil util;

  public OverAllAdapter() {

  }

  private ArrayList<String> list() {
    return new ArrayList<>(Arrays.asList(
        "Total Confirmed Cases",
        "Total Deceased", "Total Recovered",
        "Total Tested People",
        "Current number of recovered/deceased cases",
        "Mortality Prediction"));
  }

  @NotNull
  @Override
  public BaseBinderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    BaseBinderViewHolder viewHolder = null;

    switch (viewType) {
      case 0:
        CardViewLayoutBinding binding = DataBindingUtil.inflate(inflater,
            R.layout.card_view_layout, parent, false);
        viewHolder = new OverAllViewHolder(binding, util);
        break;
      case 1:
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + viewType);
    }

    assert viewHolder != null;
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull BaseBinderViewHolder holder, int position) {
    if (position == 0) {
      OverAllViewHolder allViewHolder = (OverAllViewHolder) holder;
      allViewHolder.getBinding().chartTitle.setText(list().get(position));
      allViewHolder.setLineChart();
    }
  }

  @Override
  public int getItemViewType(int position) {
    return super.getItemViewType(position);
  }

  @Override
  public int getItemCount() {
    return MAX_CARD_NUM;
  }

  @Override
  public void helperUtil(ChartHelperUtil util) {
    this.util = util;
  }
}
