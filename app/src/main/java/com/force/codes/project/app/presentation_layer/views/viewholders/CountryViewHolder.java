package com.force.codes.project.app.presentation_layer.views.viewholders;

/*
 * Created by Force Porquillo on 6/2/20 1:53 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

import android.content.res.Resources;
import android.view.View;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.databinding.CountryRowsBinding;
import com.force.codes.project.app.presentation_layer.controller.interfaces.FragmentCallback;
import com.force.codes.project.app.presentation_layer.controller.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import org.jetbrains.annotations.NotNull;

public class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
  private final FragmentCallback callback;
  private final CountryRowsBinding binding;

  public CountryViewHolder(
      @NotNull final CountryRowsBinding binding,
      final FragmentCallback callback
  ) {
    super(binding.getRoot());
    this.binding = binding;
    this.callback = callback;
    binding.getRoot().setOnClickListener(this);
  }

  @BindingAdapter({ "imageUrl" })
  public static void setFlag(@NotNull CircleImageView flagPlaceholder, final String imageUrl) {
    Glide.with(flagPlaceholder.getContext())
        .asBitmap()
        .apply(new RequestOptions()
            .fitCenter()
            .override(125, 125))
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .placeholder(R.drawable.cupertino_loading)
        .load(imageUrl)
        .into(flagPlaceholder);
  }

  public void setTextUpdate(CountryDetails details) {
    String todayCases = String.valueOf(details.getTodayCases());
    String totalCases = String.valueOf(details.getCases());

    final Resources res = binding.getRoot().getResources();
    final String NEW_CASES = res.getString(R.string.new_cases_total);
    final String NO_NEW_CASES = res.getString(R.string.no_new_cases);
    final String CONFIRMED = res.getString(R.string.confirmed_today);

    if (details.getTodayCases() != 0) {
      binding.newCasesText.setText(String.format("%s%s%s%s", Utils.formatNumber(todayCases),
          NEW_CASES, Utils.formatNumber(totalCases), CONFIRMED));
      return;
    }

    binding.newCasesText.setText(
        String.format("%s%s", Utils.formatNumber(totalCases), NO_NEW_CASES));
  }

  public void bindTo(CountryDetails details) {
    binding.setDetails(details);
    binding.setVariable(BR.details, details);
    binding.executePendingBindings();
  }

  @Override public void onClick(View v) {
    callback.cardItemListener(
        getAdapterPosition()
    );
  }
}
