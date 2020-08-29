package com.force.codes.project.app.presentation_layer.views.viewmodels

import com.force.codes.project.app.data_layer.model.PrimarySelected
import com.force.codes.project.app.data_layer.resources.database.NavHostDao
import com.force.codes.project.app.presentation_layer.controller.service.ThreadExecutor
import com.force.codes.project.app.presentation_layer.views.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class NavHostViewModel
@Inject internal constructor(
  private val dao: NavHostDao,
  private val threadExecutor: ThreadExecutor
) : BaseViewModel() {

  fun insertDefaultCountryAtFirstInstall(country: String) {
    threadExecutor.diskIO()
        .execute {
          dao.insertCountry(PrimarySelected(1, country))
        }
  }
}