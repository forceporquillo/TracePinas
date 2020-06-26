/*
 * Created by Force Porquillo on 6/20/20 8:59 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 8:59 PM
 */

package com.force.codes.project.app.data_layer.repositories.map;

import com.force.codes.project.app.data_layer.model.ListData;

import io.reactivex.Flowable;

public interface MapRepository{
    Flowable<ListData> getListDataFromNetwork();

    Flowable<ListData> getDataFromDatabase();

    void insertOrUpdate(ListData listData);
}
