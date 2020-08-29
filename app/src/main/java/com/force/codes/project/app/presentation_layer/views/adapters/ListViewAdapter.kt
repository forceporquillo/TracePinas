/*
 * Created by Force Porquillo on 8/8/20 9:30 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 30/8/20 9:30 PM
 */
package com.force.codes.project.app.presentation_layer.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.force.codes.project.app.data_layer.model.country.CountryDetails
import com.force.codes.project.app.databinding.ListViewItemsBinding
import com.force.codes.project.app.presentation_layer.controller.support.StackEventListener.OnGetAdapterPosition
import com.force.codes.project.app.presentation_layer.views.viewholders.ListViewHolder
import java.util.ArrayList

class ListViewAdapter(
  private val callback: OnGetAdapterPosition,
) : Adapter<ListViewHolder>() {

  private var detailsList: List<CountryDetails?>

  fun updateData(
    detailsList: List<CountryDetails?>,
  ) {
    this.detailsList = detailsList
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int,
  ): ListViewHolder {
    val inflater = LayoutInflater
        .from(parent.context)
    val binding = ListViewItemsBinding
        .inflate(inflater, parent, false)
    return ListViewHolder(binding, callback)
  }

  override fun onBindViewHolder(
    holder: ListViewHolder,
    position: Int,
  ) {
    holder.setBinding(detailsList[position])
  }

  override fun getItemCount(): Int {
    return detailsList.size
  }

  init {
    detailsList = ArrayList()
  }
}