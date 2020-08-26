package com.force.codes.project.app.presentation_layer.controller.support;

import android.view.View;

public interface StackEventListener {

  interface ListActivityListener {
    void onStartListViewActivity();
  }

  interface BottomItemListener {
    void onBottomBarItemSelected(final int itemId);
  }

  interface onGetAdapterPosition {
    void onItemClicked(final int index);
  }

  interface NewsItemCallback {
    void onRecentTweetsListener(final int index);
    void onLocalNewsListener(final int index);
  }

  interface LiveStateResponse {
    void onErrorResponse(boolean isError);
  }

  interface ViewTopRegionsListener {
    void onViewAllRegions(View view);
    void onRegionSelected(View view);
  }
}
