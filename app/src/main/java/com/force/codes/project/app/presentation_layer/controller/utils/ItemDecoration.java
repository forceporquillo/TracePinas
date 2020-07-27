package com.force.codes.project.app.presentation_layer.controller.utils;

/*
 * Created by Force Porquillo on 6/14/20 3:25 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class ItemDecoration extends RecyclerView.ItemDecoration{
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private Drawable drawable;
    private int orientation;
    private Context context;
    private int margin;

    public ItemDecoration(Context context, int orientation, int margin){
        this.context = context;
        this.margin = margin;
        final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        drawable = typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation){
        if(orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST){
            throw new IllegalArgumentException("invalid orientation");
        }
        this.orientation = orientation;
    }

    @Override
    public void onDrawOver(@NotNull Canvas canvas, @NotNull RecyclerView parent, @NotNull RecyclerView.State state){
        if(orientation == VERTICAL_LIST) drawVertical(canvas, parent);
        else drawHorizontal(canvas, parent);
    }

    public void drawVertical(Canvas canvas, RecyclerView parent){
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left + dpToPx(margin), top, right - dpToPx(margin), bottom);
            drawable.draw(canvas);
        }
    }

    public void drawHorizontal(Canvas canvas, RecyclerView parent){
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top + dpToPx(margin), right, bottom - dpToPx(margin));
            drawable.draw(canvas);
        }
    }

    @Override
    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state){
        if(orientation == VERTICAL_LIST){
            outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
            return;
        }
        outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
    }

    private int dpToPx(int dp){
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}




