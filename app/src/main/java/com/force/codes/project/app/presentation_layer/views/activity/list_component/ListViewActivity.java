/*
 * Created by Force Porquillo on 8/8/20 8:13 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/8/20 8:13 PM
 */

package com.force.codes.project.app.presentation_layer.views.activity.list_component;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.databinding.ActivityListViewBinding;
import com.force.codes.project.app.presentation_layer.controller.interfaces.ListItemListener;
import com.force.codes.project.app.presentation_layer.controller.interfaces.ListViewCallback;
import com.force.codes.project.app.presentation_layer.controller.utils.ItemDecoration;
import com.force.codes.project.app.presentation_layer.views.activity.BaseActivity;
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory;
import com.force.codes.project.app.presentation_layer.views.fragments.viewpager.MyCountryFragment;
import com.force.codes.project.app.presentation_layer.views.viewmodels.ListViewModel;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

public class ListViewActivity extends BaseActivity implements ListViewCallback {
  private ActivityListViewBinding binding;
  private ListViewModel viewModel;
  private List<CountryDetails> details;

  @Inject ViewModelProviderFactory factory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_view);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_list_view);

    binding.setListActivity(this);
    binding.setLifecycleOwner(this);
    binding.setVariable(BR.listActivity, this);

    viewModel = new ViewModelProvider(this, factory).get(ListViewModel.class);

    binding.toolbar.setTitle("");
    setSupportActionBar(binding.toolbar);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    binding.executePendingBindings();
  }

  @Override protected void onStart() {
    super.onStart();
    viewModel.getCountryData().observe(this, countryDetails -> {
      details = countryDetails;
      setRecyclerView(countryDetails);
    });
  }

  private void setRecyclerView(final List<CountryDetails> details) {
    binding.listRecycler.setLayoutManager(new LinearLayoutManager(this));
    binding.listRecycler.addItemDecoration(new ItemDecoration(
        this, ItemDecoration.VERTICAL_LIST, 0));
    binding.listRecycler.setAdapter(new ListAdapter(details, this));
    binding.invalidateAll();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (binding != null) {
      binding.unbind();
      binding = null;
    }
    if (details != null) {
      details.clear();
      details = null;
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      this.onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void getPosition(int position) {
    viewModel.insertSelectedCountry(details.get(position).getCountry());
    finish();
  }
}