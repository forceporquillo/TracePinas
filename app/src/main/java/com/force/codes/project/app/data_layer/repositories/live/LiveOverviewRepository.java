package com.force.codes.project.app.data_layer.repositories.live;

/*
 * Created by Force Porquillo on 6/4/20 3:32 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 3:32 AM
 *
 */

import com.force.codes.project.app.data_layer.model.WorldData;

import io.reactivex.Flowable;

public interface LiveOverviewRepository{
    Flowable<WorldData> getWorldDataFromNetwork();
}
