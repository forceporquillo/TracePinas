/*
 * Created by Force Porquillo on 7/17/20 9:22 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/17/20 9:22 AM
 */

package com.force.codes.project.app.presentation_layer.controller.custom.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public abstract class RuntimeMargin{
    public static int getPixelValue(Context context, int densityPixel){
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                densityPixel,
                resources.getDisplayMetrics()
        );
    }
}
