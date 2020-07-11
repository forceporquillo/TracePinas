/*
 * Created by Force Porquillo on 7/1/20 7:09 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/30/20 3:12 AM
 */

package com.force.codes.project.app.data_layer.repositories.interfaces;

/*
 * Created by Force Porquillo on 6/4/20 3:32 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/4/20 3:32 AM
 *
 */

import com.force.codes.project.app.data_layer.model.map_data.WorldData;

import io.reactivex.Flowable;

public interface LiveOverviewRepository{
    Flowable <WorldData> getWorldDataFromNetwork();
}
