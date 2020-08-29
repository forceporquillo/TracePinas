package com.force.codes.project.app.presentation_layer.views.viewmodels

import androidx.lifecycle.MutableLiveData
import com.force.codes.project.app.data_layer.model.PrimarySelected
import com.force.codes.project.app.data_layer.model.country.CountryDetails
import com.force.codes.project.app.data_layer.resources.database.ListViewDao
import com.force.codes.project.app.presentation_layer.controller.service.ThreadExecutor
import com.force.codes.project.app.presentation_layer.views.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ListViewModel
@Inject internal constructor(
  private val listViewDao: ListViewDao,
  private val threadExecutor: ThreadExecutor,
) : BaseViewModel() {

  private val liveData: MutableLiveData<List<CountryDetails?>> by lazy {
    MutableLiveData()
  }

  val getLiveData: MutableLiveData<List<CountryDetails?>>
    get() {
      getListLiveData(true)
      return liveData
    }

  fun setPrimarySelected(country: String) {
    threadExecutor.diskIO()
        .execute {
          listViewDao.insertSelected(
              PrimarySelected(1, country)
          )
        }
  }

  fun orderListViewBy(defaultOrder: Boolean) {
    getListLiveData(defaultOrder)
  }

  private fun getListLiveData(
    defaultOrder: Boolean,
  ): MutableLiveData<List<CountryDetails?>> {
    val disposable = listViewDao.queryListViewBy(defaultOrder)
        .observeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          liveData.value = it
        }) { t: Throwable -> Timber.e(t) }
    addToUnsubscribed(disposable)
    return liveData
  }
}