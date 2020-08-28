package com.force.codes.project.app.presentation_layer.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.data_layer.model.doh_data_drop_csv.PHProvinces;
import com.force.codes.project.app.databinding.LayoutTopRegionsBinding;
import com.force.codes.project.app.presentation_layer.views.viewholders.TopRegionsViewHolder;
import java.util.List;

public class TopRegionsAdapter extends RecyclerView.Adapter<TopRegionsViewHolder> {
  private final List<PHProvinces> data;

  public TopRegionsAdapter(List<PHProvinces> data) {
    this.data = data;
  }

  @NonNull @Override
  public TopRegionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    LayoutTopRegionsBinding binding = LayoutTopRegionsBinding
        .inflate(inflater, parent, false);
    return new TopRegionsViewHolder(binding);
  }

  @Override public void onBindViewHolder(@NonNull TopRegionsViewHolder holder, int position) {
    PHProvinces item = data.get(position);

    holder.setBinding(item);
  }

  @Override public int getItemCount() {
    return data.size();
  }
}
