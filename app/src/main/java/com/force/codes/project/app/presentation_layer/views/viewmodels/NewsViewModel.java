/*
 * Created by Force Porquillo on 7/1/20 4:04 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/1/20 4:01 AM
 */

package com.force.codes.project.app.presentation_layer.views.viewmodels;

/*
 * Created by Force Porquillo on 6/4/20 6:06 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 6:06 AM
 *
 */

import androidx.lifecycle.MutableLiveData;

import com.force.codes.project.app.data_layer.model.NewsData;
import com.force.codes.project.app.data_layer.repositories.interfaces.NewsRepository;

import javax.inject.Inject;
import javax.inject.Named;

public class NewsViewModel extends BaseViewModel{
    private NewsRepository newsRepository;

    @Inject
    @Named("NewsVM")
    MutableLiveData<NewsData> liveData;

    @Inject
    public NewsViewModel(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }
}
