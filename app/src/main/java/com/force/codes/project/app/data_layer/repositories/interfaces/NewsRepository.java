/*
 * Created by Force Porquillo on 7/9/20 5:37 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/9/20 5:37 AM
 */

package com.force.codes.project.app.data_layer.repositories.interfaces;

import com.force.codes.project.app.data_layer.model.NewsData;

import io.reactivex.Flowable;

public interface NewsRepository{
    Flowable<NewsData> getNewsResponse();
}
