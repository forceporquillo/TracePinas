package com.force.codes.project.app.presentation_layer.controller.custom.utils;

/*
 * Created by Force Porquillo on 6/5/20 3:13 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/5/20 4:02 AM
 *
 */

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.RequiresApi;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.os.Build.VERSION_CODES.N;
import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

public class StringUtils extends DateUtils{
    public static String getDate(long milliseconds){
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss aaa", Locale.getDefault());
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

    public static String getDate(){
        Date calendar = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd, MMMM yyyy");
        return dateFormat.format(calendar);
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
