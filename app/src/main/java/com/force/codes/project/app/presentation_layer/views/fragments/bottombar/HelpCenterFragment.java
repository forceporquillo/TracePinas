/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:01 AM
 */

package com.force.codes.project.app.presentation_layer.views.fragments.bottombar;

/*
 * Created by Force Porquillo on 6/11/20 12:22 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.views.factory.ViewModelProviderFactory;
import com.force.codes.project.app.presentation_layer.views.viewmodels.HelpCenterViewModel;
import javax.inject.Inject;

public class HelpCenterFragment extends Fragment {

  @Inject
  ViewModelProviderFactory factory;
  private HelpCenterViewModel mViewModel;

  public HelpCenterFragment() {

  }

  public static HelpCenterFragment newInstance() {
    return new HelpCenterFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.favorites_fragment, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    //mViewModel = new ViewModelProvider(this, factory).get(HelpCenterViewModel.class);
    // TODO: Use the ViewModel
  }
}