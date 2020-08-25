package com.force.codes.project.app.presentation_layer.views.viewholders;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.data_layer.model.philippines.DataItem;
import com.force.codes.project.app.data_layer.model.philippines.TopRegions;
import com.force.codes.project.app.databinding.LayoutTopRegionsBinding;

public class TopRegionsViewHolder extends RecyclerView.ViewHolder {
  private LayoutTopRegionsBinding binding;

  public TopRegionsViewHolder(@NonNull LayoutTopRegionsBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void setBinding(DataItem item) {
    binding.setDataItem(item);
    binding.invalidateAll();
  }
}
