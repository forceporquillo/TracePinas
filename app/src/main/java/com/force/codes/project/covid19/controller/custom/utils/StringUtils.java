package com.force.codes.project.covid19.controller.custom.utils;

/*
 * Created by Force Porquillo on 6/2/20 1:49 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 12:55 PM
 *
 */

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.RequiresApi;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.os.Build.VERSION_CODES.N;
import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

public class StringUtils{

    private static final String TAG = StringUtils.class.getSimpleName();
    private static final String BUNDLE_KEY = "global_key";

    public String getDate(long milliseconds){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss aaa");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);

        return formatter.format(calendar.getTime());
    }

    public String formatNumber(String number){
        if(!number.isEmpty()){
            double val = Double.parseDouble(number);
            return NumberFormat.getNumberInstance(Locale.US).format(val);
        } else{
            return "0";
        }
    }

    public SpannableString spannableString(int colorPosition, String string){
        SpannableString spannableString = new SpannableString(string);
        ForegroundColorSpan colorSpan;

        switch(colorPosition){
            case 1: // blue
                colorSpan = new ForegroundColorSpan(Color.rgb(50, 120, 210));
                spannableString.setSpan(colorSpan, 5, string.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case 2: // red
                colorSpan = new ForegroundColorSpan(Color.rgb(255, 93, 93));
                spannableString.setSpan(colorSpan, 5, string.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case 3: // blue
                colorSpan = new ForegroundColorSpan(Color.rgb(50, 120, 210));
                spannableString.setSpan(colorSpan, 0, string.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
        }

        return spannableString;
    }

    public String getDate(){
        Date calendar = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd, MMMM yyyy");
        return dateFormat.format(calendar);
    }


    @RequiresApi(api = N)
    public String toPercent(double num, double total){
        DecimalFormat df = new DecimalFormat("##%");
        double percent = (num / total);
        return df.format(percent);
    }

}
