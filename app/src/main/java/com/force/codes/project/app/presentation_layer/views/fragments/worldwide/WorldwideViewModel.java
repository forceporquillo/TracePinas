package com.force.codes.project.app.presentation_layer.views.fragments.worldwide;

/*
 * Created by Force Porquillo on 6/2/20 1:22 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

import androidx.databinding.ObservableLong;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.force.codes.project.app.data_layer.repositories.worldwide.WorldwideRepository;
import com.force.codes.project.app.model.CountryDetails;
import com.force.codes.project.app.presentation_layer.controller.custom.interfaces.OnRequestResponse;
import com.force.codes.project.app.service.executors.AppExecutors;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class WorldwideViewModel extends ViewModel{
    private final WorldwideRepository repository;
    private final AppExecutors appExecutors;
    private final CompositeDisposable compositeDisposable;

    private LiveData<PagedList<CountryDetails>> listLiveData;
    private OnRequestResponse requestResponse;
    private ObservableLong longDate;

    public WorldwideViewModel(
            final WorldwideRepository repository, final AppExecutors appExecutors,
            final CompositeDisposable disposable, final OnRequestResponse response,
            final ObservableLong observableLong){
        this.repository = repository;
        this.appExecutors = appExecutors;
        this.compositeDisposable = disposable;
        this.requestResponse = response;
        this.longDate = observableLong;
    }

    /**
     * Custom lazy loading configurations to our pageList adapter
     * with an initial load of 100 and page size of 70.
     */
    static final PagedList.Config config = new PagedList.Config.Builder()
            .setPageSize(11)
            .setMaxSize(215)
            .setEnablePlaceholders(false)
            .build();

    /**
     * Check if it is null if !null {@return liveData} from local database.
     * If local database emits == null or no items call {@method getDataFromNetwork}
     * to insert or update data from local database.
     * <p>
     * This {@return LiveData} from local database.
     */
    public LiveData<PagedList<CountryDetails>> getDataFromDatabase(){
        if(listLiveData == null){
            return listLiveData = repository.getDataFromDatabase(config);
        }

        return listLiveData;
    }

    /**
     * This emits large size of data from remote repository and save to local database.
     * <p>
     * This {@method getDataFromNetwork()} is not directly subscribed to the main thread
     * to prevent blocking the UI. And, Since our client API doesn't support pagination,
     * we instead subscribed to a local repository whenever data changed.
     */
    public void getDataFromNetwork(){
        compositeDisposable.add(Flowable.just(1)
                .subscribeOn(Schedulers.computation())
                .flatMap(list -> {
                    Timber.d("fetching data from server...");
                    return repository.getDataFromRemoteService();
                })
                .subscribeOn(Schedulers.io())
                .doOnNext(time -> {
                    Timber.d("time update successful...");
                    longDate.set(time.get(0).getUpdated());
                })
                .observeOn(Schedulers.computation())
                .doOnError(error -> {
                    requestResponse.onErrorResponse(true);
                    Timber.d("Error, Failed to attempt force update...");
                })
                .subscribe(list -> {
                    repository.saveDatabase(list);
                    Timber.d("inserting data to database...");
                }, Throwable::printStackTrace)
        );
    }

    /**
     * This {@return null} when in offline mode and observable Long when
     * connected. We only emmit updated time from remote service.
     * <p>
     * This observes changes when connected to the network.
     */
    public ObservableLong getTimeUpdate(){
        return longDate;
    }

    /**
     * This updates our favorite local repository
     * table whenever we add or remove data.
     * <p>
     * {@link AppExecutors} database queries are run in a custom ThreadPool.
     *
     * @param details providing data to update our local repository.
     */
    public void addOrRemoveFavorites(CountryDetails details){
        appExecutors.diskIO().execute(() ->
                repository.updateFavorites(details));
    }

    /**
     * This disposed our {@link Flowable} observable to prevent memory leaks.
     * This method will be called whenever this {@link ViewModel} is no
     * longer used and or {@link WorldwideFragment} has been destroyed.
     * <p>
     * We attached our {@link CompositeDisposable} in this {@method onCleared()}
     * to help us ease in clearing observables when ending a stream.
     * <p>
     * Since this {@link WorldwideViewModel} is lifeCycle aware to its
     * parent host {@link WorldwideFragment}.
     */
    @Override
    protected void onCleared(){
        super.onCleared();
        if(compositeDisposable != null && !compositeDisposable.isDisposed()){
            compositeDisposable.clear();
        }
    }

    public void forceUpdate(){
        getDataFromNetwork();
    }
}

