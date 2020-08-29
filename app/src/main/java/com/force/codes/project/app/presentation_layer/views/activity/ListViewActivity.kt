package com.force.codes.project.app.presentation_layer.views.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.force.codes.project.app.R
import com.force.codes.project.app.data_layer.model.country.CountryDetails
import com.force.codes.project.app.databinding.ActivityListViewBinding
import com.force.codes.project.app.presentation_layer.controller.layout.ItemDecoration
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener.OnGetAdapterPosition
import com.force.codes.project.app.presentation_layer.views.adapters.ListViewAdapter
import com.force.codes.project.app.presentation_layer.views.base.BaseActivity
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory
import com.force.codes.project.app.presentation_layer.views.viewmodels.ListViewModel
import javax.inject.Inject

class ListViewActivity : BaseActivity(), OnGetAdapterPosition {

  private lateinit var viewModel: ListViewModel

  private lateinit var binding: ActivityListViewBinding

  private lateinit var detailsList: List<CountryDetails?>

  private lateinit var listViewAdapter: ListViewAdapter

  @Inject lateinit var factory: ViewModelProviderFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_list_view)

    binding.apply {
      listActivity = this@ListViewActivity
      lifecycleOwner = this@ListViewActivity
      toolbar.title = ""
    }

    savedInstanceState.let {
      viewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)
      setSupportActionBar(binding.toolbar)
    }

    listViewAdapter = ListViewAdapter(this)

    supportActionBar?.let {
      it.setDisplayShowHomeEnabled(true)
      it.setDisplayHomeAsUpEnabled(true)
    }

    binding.listRecycler.apply {
      layoutManager = LinearLayoutManager(
          this@ListViewActivity
      )
      addItemDecoration(
          ItemDecoration(
              this@ListViewActivity,
              ItemDecoration.VERTICAL_LIST,
              0
          )
      )
      adapter = listViewAdapter
      setHasFixedSize(true)
    }

    binding.hasPendingBindings()
        .apply {
          binding.executePendingBindings()
        }
  }

  override fun onStart() {
    super.onStart()
    viewModel.getLiveData.observe(this, { details ->
      listViewAdapter.updateData(details)
      detailsList = details
    })
  }

  override fun onDestroy() {
    super.onDestroy()
    binding.unbind()
  }

  override fun onItemClicked(
    index: Int,
  ) {
    viewModel.setPrimarySelected(
        detailsList[index]!!.country
    )
    finish()
  }

  override fun onOptionsItemSelected(
    item: MenuItem,
  ): Boolean {
    var order = true
    when (item.itemId) {
      android.R.id.home -> {
        onBackPressed()
        return true
      }
      R.id.order_cases -> order = true
      R.id.order_alphabetical -> order = false
    }
    viewModel.orderListViewBy(order)
    binding.invalidateAll()
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(
    menu: Menu?,
  ): Boolean {
    menuInflater.inflate(R.menu.menu_items, menu)
    return true
  }
}


