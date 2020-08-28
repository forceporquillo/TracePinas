package com.force.codes.project.app.presentation_layer.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.doh_data_drop_csv.PHProvinces;
import java.util.ArrayList;
import java.util.List;

public class TopRegionsDialogAdapter extends BaseAdapter {
  private List<PHProvinces> provinces = new ArrayList<>();

  public TopRegionsDialogAdapter(final List<PHProvinces> provinces) {
    if (provinces != null) {
      provinces.add(0, new PHProvinces("ALL REGIONS"));
      this.provinces.addAll(provinces);
    }
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    if (convertView == null) {
      if (!provinces.isEmpty()) {
        convertView = inflater.inflate(R.layout.list_holder_curve, parent, false);
        final TextView regions = convertView.findViewById(R.id.ph_regions);
        regions.setText(getItemPosition(position).toUpperCase());
        return convertView;
      }
    }
    return convertView;
  }

  @Override public int getItemViewType(int position) {
    return position;
  }

  @Override public int getViewTypeCount() {
    return provinces.size() == 0 ? 1 : provinces.size();
  }

  private String getItemPosition(final int index) {
    return this.provinces.get(index).getRegion();
  }

  @Override public int getCount() {
    return provinces.size();
  }

  @Override public Object getItem(int position) {
    return position;
  }

  @Override public long getItemId(int position) {
    return position;
  }

}
