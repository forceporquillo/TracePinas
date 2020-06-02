package com.force.codes.project.covid19.controller.support;

/*
 * Created by Force Porquillo on 6/2/20 1:49 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 12:55 PM
 *
 */

import android.util.Log;

import com.force.codes.project.covid19.R;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.muddzdev.styleabletoast.StyleableToast;

public class CustomBindingAdapter{
    private static final String LIKED_MESSAGE = " has been added to favorites";
    private static final String UNLIKE_MESSAGE = " remove from favorites";

    public void setStar(int position, LikeButton starButton, final String countryName){
        starButton.setOnLikeListener(new OnLikeListener(){
            @Override
            public void liked(LikeButton likeButton){
                // region -> TODO save to a new ArrayList and store to local DB as favorites.
                Log.isLoggable(null, position); // for list manipulation
                // endregion
                StyleableToast.makeText(starButton.getContext(),
                        countryName + LIKED_MESSAGE, R.style.custom_toast_style).show();
            }

            @Override
            public void unLiked(LikeButton likeButton){
                StyleableToast.makeText(starButton.getContext(), countryName + UNLIKE_MESSAGE, R.style.custom_toast_style).show();
                // endregion
            }
        });
    }
}
