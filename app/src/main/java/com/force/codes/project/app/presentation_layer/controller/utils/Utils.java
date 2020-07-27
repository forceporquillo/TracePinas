package com.force.codes.project.app.presentation_layer.controller.utils;

/*
 * Created by Force Porquillo on 6/5/20 3:13 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/5/20 4:02 AM
 *
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;

import androidx.annotation.RequiresApi;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.os.Build.VERSION_CODES.N;
import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

/**
 * Utilities class that has some helper methods.
 * This class doesn't need an instance of a class itself.
 *
 * @author Force Porquillo
 */

public abstract class Utils{
    public static String getDate(long milliseconds){
        SimpleDateFormat formatter = simpleDateFormat("EEE, dd MMM yyyy hh:mm:ss aaa");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return formatter.format(calendar.getTime());
    }

    public static String formatNumber(String number){
        if(!number.isEmpty()){
            double value = Double.parseDouble(number);
            return NumberFormat.getNumberInstance(Locale.US).format(value);
        }
        return "0";
    }

    public static int getSDKInt(){
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * @return thread count by multiplying device processors count by 2
     */

    public static int getThreadCount(){
        return Runtime.getRuntime().availableProcessors() * 2;
    }

    public static String getDate(){
        Date calendar = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = simpleDateFormat("yyyy-mm-dd");
        return dateFormat.format(calendar);
    }

    /**
     * A helper method to manually align view margin at runtime.
     * This converts dp to px.
     * @return pixel based on device dpi and resolution.
     */
    public static int getPixelValue(Context context, int densityPixel){
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                densityPixel,
                resources.getDisplayMetrics()
        );
    }

    @SuppressLint("SimpleDateFormat")
    public static SimpleDateFormat simpleDateFormat(String format){
        return new SimpleDateFormat(format);
    }

    public static Date getTodayDate(){
        return new Date();
    }

    @RequiresApi(api = N)
    public static String toPercent(double num, double total){
        DecimalFormat df = new DecimalFormat("##%");
        double percent = (num / total);
        return df.format(percent);
    }

    public static SpannableString spannableString(String color, String string){
        SpannableString spannableString = new SpannableString(string);
        ForegroundColorSpan colorSpan;

        switch(color){
            case "BLUE": // blue
                colorSpan = new ForegroundColorSpan(Color.rgb(50, 120, 210));
                spannableString.setSpan(colorSpan, 1, string.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case "RED": // red
                colorSpan = new ForegroundColorSpan(Color.rgb(255, 93, 93));
                spannableString.setSpan(colorSpan, 1, string.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
        }

        return spannableString;
    }
}
