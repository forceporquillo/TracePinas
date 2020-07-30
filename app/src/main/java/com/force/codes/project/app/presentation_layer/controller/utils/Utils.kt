package com.force.codes.project.app.presentation_layer.controller.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.icu.text.DecimalFormat
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import androidx.annotation.RequiresApi
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/*
 * Created by Force Porquillo on 6/5/20 3:13 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/5/20 4:02 AM
 *
 */ /**
 * Utilities class that has some helper methods.
 * This class doesn't need an instance of a class itself.
 *
 * @author Force Porquillo
 */
object Utils {
  @JvmStatic
  fun getLongDate(milliseconds: Long): String {
    val formatter =
      simpleDateFormat(
          "EEE, dd MMM yyyy hh:mm:ss aaa"
      )
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milliseconds
    return formatter.format(calendar.time)
  }

  @JvmStatic fun formatNumber(number: String): String {
    if (number.isNotEmpty()) {
      val value = number.toDouble()
      return NumberFormat.getNumberInstance(Locale.US)
          .format(value)
    }
    return "0"
  }

  @JvmStatic val sDKInt: Int
    get() = VERSION.SDK_INT

  @JvmStatic fun requiresSdkInt(version: Int): Boolean {
    return sDKInt > version
  }

  /**
   * @return thread count by multiplying device processors count by 2
   */
  @JvmStatic val threadCount: Int
    get() = Runtime.getRuntime()
        .availableProcessors() * 2

  @JvmStatic val date: String
    get() {
      val calendar = Calendar.getInstance()
          .time
      @SuppressLint("SimpleDateFormat") val dateFormat =
        simpleDateFormat(
            "yyyy-mm-dd"
        )
      return dateFormat.format(calendar)
    }

  /**
   * A helper method to manually align view margin at runtime.
   * This converts dp to px.
   *
   * @return pixel based on device dpi and resolution.
   */
  @JvmStatic fun getPixelValue(
    context: Context,
    densityPixel: Int
  ): Int {
    val resources = context.resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        densityPixel.toFloat(),
        resources.displayMetrics
    )
        .toInt()
  }

  @JvmStatic @SuppressLint("SimpleDateFormat")
  fun simpleDateFormat(format: String?): SimpleDateFormat {
    return SimpleDateFormat(format)
  }

  @JvmStatic val todayDate: Date
    get() = Date()

  @JvmStatic @RequiresApi(api = VERSION_CODES.N) fun toPercent(
    num: Double,
    total: Double
  ): String {
    val df = DecimalFormat("##%")
    val percent = num / total
    return df.format(percent)
  }

  @JvmStatic fun spannableString(
    color: String?,
    string: String
  ): SpannableString {
    val spannableString = SpannableString(string)
    val colorSpan: ForegroundColorSpan
    when (color) {
      "BLUE" -> {
        colorSpan = ForegroundColorSpan(Color.rgb(50, 120, 210))
        spannableString.setSpan(colorSpan, 1, string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
      }
      "RED" -> {
        colorSpan = ForegroundColorSpan(Color.rgb(255, 93, 93))
        spannableString.setSpan(colorSpan, 1, string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
      }
    }
    return spannableString
  }
}