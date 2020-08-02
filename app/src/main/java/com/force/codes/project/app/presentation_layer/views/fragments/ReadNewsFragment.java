/*
 * Created by Force Porquillo on 7/8/20 1:40 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/8/20 1:40 AM
 */

package com.force.codes.project.app.presentation_layer.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.force.codes.project.app.BR;
import com.force.codes.project.app.data_layer.model.news.ArticlesItem;
import com.force.codes.project.app.databinding.FragmentReadNewsBinding;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadNewsFragment extends Fragment {

  private static final String TITLE_ARGS = "title_args";
  private static final String IMAGE_ARGS = "image_args";
  private FragmentReadNewsBinding binding;

  public ReadNewsFragment() {
    // Required empty public constructor
  }

  public static ReadNewsFragment newInstance(final ArticlesItem articlesItem) {
    ReadNewsFragment fragment = new ReadNewsFragment();
    Bundle args = new Bundle();
    args.putString(TITLE_ARGS, articlesItem.getTitle());
    args.putString(IMAGE_ARGS, articlesItem.getUrlToImage());
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      Timber.i("not null");
    }
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentReadNewsBinding.inflate(inflater, container, false);
    binding.setReadNews(this);
    binding.setLifecycleOwner(this);
    binding.setVariable(BR.readNews, this);
    binding.invalidateAll();
    return binding.getRoot();
  }

  @Override
  public void onResume() {
    super.onResume();
    Glide.with(binding.image.getContext())
        .asBitmap()
        .load(IMAGE_ARGS)
        .centerCrop()
        .into(binding.image);
    binding.title.setText(TITLE_ARGS);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding.unbind();
    binding = null;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }
}