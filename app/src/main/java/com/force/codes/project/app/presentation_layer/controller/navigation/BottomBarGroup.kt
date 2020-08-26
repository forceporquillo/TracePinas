/*
 * Created by Force Porquillo on 7/6/20 2:19 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/6/20 2:19 AM
 */
package com.force.codes.project.app.presentation_layer.controller.navigation

class BottomBarGroup {
  var groupTitle: String? = null
  var isSpinner = false

  constructor()
  constructor(
    title: String?,
    spinner: Boolean
  ) {
    groupTitle = title
    isSpinner = spinner
  }

}