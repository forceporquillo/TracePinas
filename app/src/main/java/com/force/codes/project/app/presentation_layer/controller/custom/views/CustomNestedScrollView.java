package com.force.codes.project.app.presentation_layer.controller.custom.views;

/*
 * Created by Force Porquillo on 6/2/20 1:58 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 12:55 PM
 *
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.core.widget.NestedScrollView;

public class CustomNestedScrollView extends NestedScrollView{

    private boolean enableScrolling = true;

    public CustomNestedScrollView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    public CustomNestedScrollView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public CustomNestedScrollView(Context context){
        super(context);
    }

    public boolean isEnableScrolling(){
        return enableScrolling;
    }

    public void setEnableScrolling(boolean enableScrolling){
        this.enableScrolling = enableScrolling;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){

        if(isEnableScrolling()){
            return super.onInterceptTouchEvent(ev);
        } else{
            return false;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev){
        if(isEnableScrolling()){
            return super.onTouchEvent(ev);
        } else{
            return false;
        }
    }
}
