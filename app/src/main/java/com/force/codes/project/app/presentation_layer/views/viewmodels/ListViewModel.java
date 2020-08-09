package com.force.codes.project.app.presentation_layer.views.viewmodels;

import androidx.lifecycle.MutableLiveData;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.data_layer.repositories.interfaces.ListViewRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

public class ListViewModel extends BaseViewModel {
  private final ListViewRepository listViewRepository;
  private final MutableLiveData<List<CountryDetails>> listMutableLiveData;

  @Inject
  ListViewModel(ListViewRepository listViewRepository) {
    this.listViewRepository = listViewRepository;
    this.listMutableLiveData = new MutableLiveData<>();
  }

  public MutableLiveData<List<CountryDetails>> getCountryData() {
    Disposable disposable = listViewRepository.getCountryDetails()
        .observeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread())
        .subscribe(listMutableLiveData::setValue, Timber::e);

    addToUnsubscribed(disposable);

    return listMutableLiveData;
  }

  public void insertSelectedCountry(String country) {
    listViewRepository.insertSelected(country);
  }
}
