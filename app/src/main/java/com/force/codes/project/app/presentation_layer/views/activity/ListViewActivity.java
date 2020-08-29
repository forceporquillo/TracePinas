/*
 * Created by Force Porquillo on 8/8/20 8:13 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/8/20 8:13 PM
 */

package com.force.codes.project.app.presentation_layer.views.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.R;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.databinding.ActivityListViewBinding;
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener;
import com.force.codes.project.app.presentation_layer.controller.layout.ItemDecoration;
import com.force.codes.project.app.presentation_layer.views.adapters.ListAdapter;
import com.force.codes.project.app.presentation_layer.views.base.BaseActivity;
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory;
import com.force.codes.project.app.presentation_layer.views.viewmodels.ListViewModel;
import java.util.List;
import javax.inject.Inject;

public class ListViewActivity extends BaseActivity
    implements StackEventListener.onGetAdapterPosition {
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
    binding.toolbar.setTitle("");

    if (savedInstanceState == null) {
      viewModel = new ViewModelProvider(this, factory).get(ListViewModel.class);
      setSupportActionBar(binding.toolbar);
    }
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    if (binding.hasPendingBindings()) {
      binding.executePendingBindings();
    }
  }

  @Override protected void onStart() {
    super.onStart();
    viewModel.getGetLiveData().observe(this, this::setRecyclerView);
  }

  final void setRecyclerView(final List<CountryDetails> details) {
    final RecyclerView rv = binding.listRecycler;
    rv.setLayoutManager(new LinearLayoutManager(this));
    rv.addItemDecoration(new ItemDecoration(this, ItemDecoration.VERTICAL_LIST, 0));
    rv.setAdapter(new ListAdapter(this.details = details, this));
    binding.invalidateAll();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    boolean order = true;
    switch (item.getItemId()) {
      case android.R.id.home:
        this.onBackPressed();
        return true;
      case R.id.order_cases:
        order = true;
        break;
      case R.id.order_alphabetical:
        order = false;
        break;
    }
    viewModel.orderListViewBy(order);
    binding.invalidateAll();
    return super.onOptionsItemSelected(item);
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

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_items, menu);
    return true;
  }

  @Override public void onItemClicked(int index) {
    viewModel.insertSelectedCountry(details.get(index).getCountry());
    finish();
  }


}
