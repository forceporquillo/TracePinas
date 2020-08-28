package com.force.codes.project.app.presentation_layer.views.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.data_layer.model.doh_data_drop_csv.PHProvinces;
import com.force.codes.project.app.databinding.LayoutTopRegionsBinding;

public class TopRegionsViewHolder extends RecyclerView.ViewHolder {
  private LayoutTopRegionsBinding binding;

  public TopRegionsViewHolder(@NonNull LayoutTopRegionsBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void setBinding(PHProvinces provinces) {
    binding.setPhProvinces(provinces);
    binding.invalidateAll();
  }
}
