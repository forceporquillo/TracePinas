/*
 * Created by Force Porquillo on 7/7/20 10:17 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/7/20 10:17 PM
 */
package com.force.codes.project.app.presentation_layer.controller.interfaces

interface NewsItemCallback {
  fun recentTweetsItemListener(position: Int)
  fun hotNewsItemListener(position: Int)
}