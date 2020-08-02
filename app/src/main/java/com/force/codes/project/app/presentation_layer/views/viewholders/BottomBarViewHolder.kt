/*
 * Created by Force Porquillo on 6/16/20 8:30 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/2/20 7:24 PM
 */

package com.force.codes.project.app.presentation_layer.views.viewholders

import android.graphics.Color
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.force.codes.project.app.databinding.BottombarItemBinding

class BottomBarViewHolder(
  private val itemBinding: BottombarItemBinding
) : ViewHolder(itemBinding.root) {

  val parentContainer: RelativeLayout
    get() = itemBinding.bottomItemParent

  fun setItemTitle(title: String?) {
    itemBinding.bottomBarTitle.text = title!!
  }

  fun setIcon(iconId: Int?) {
    itemBinding.bottomIcon
        .setImageResource(
            iconId!!
        )
  }

  fun selectedStyle(
    select: Int,
    itemId: Int,
    itemDefIcon: Int,
    itemFillIcon: Int
  ) {
    return if (itemId == select) {
      itemBinding.bottomBarTitle
          .setTextColor(
              Color.rgb(50, 121, 210)
          )
      itemBinding.bottomIcon
          .setImageResource(
              itemFillIcon
          )
    } else {
      itemBinding.bottomBarTitle
          .setTextColor(
              Color.rgb(191, 191, 191)
          )
      itemBinding.bottomIcon
          .setImageResource(
              itemDefIcon
          )
    }
  }

  fun resizeItemWidth(
    itemWidth: Int
  ) {
    itemBinding.bottomParent
        .layoutParams.width = itemWidth
  }
}