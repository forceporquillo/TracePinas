/*
 * Created by Force Porquillo on 8/8/20 8:13 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/8/20 8:13 PM
 */

package com.force.codes.project.app.presentation_layer.views.activity.list_component;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.databinding.ActivityListViewBinding;
import com.force.codes.project.app.presentation_layer.controller.interfaces.ListItemListener;
import com.force.codes.project.app.presentation_layer.controller.utils.ItemDecoration;
import com.force.codes.project.app.presentation_layer.views.activity.BaseActivity;
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory;
import com.force.codes.project.app.presentation_layer.views.viewmodels.ListViewModel;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

public class ListViewActivity extends BaseActivity implements ListItemListener {
  private ActivityListViewBinding binding;
  private ListViewModel viewModel;

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
    viewModel.getCountryData().observe(this, this::setRecyclerView);
  }

  private void setRecyclerView(List<CountryDetails> details) {
    binding.listRecycler.setLayoutManager(new LinearLayoutManager(this));
    binding.listRecycler.addItemDecoration(new ItemDecoration(
        this, ItemDecoration.VERTICAL_LIST, 0)
    );
    binding.listRecycler.setAdapter(new ListAdapter(details));
    binding.invalidateAll();
  }

  @Override public void getResult(List<CountryDetails> details) {

  }

  @Override protected void onDestroy() {
    super.onDestroy();
    Timber.e("onDestroy called");
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      this.onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
  }
}