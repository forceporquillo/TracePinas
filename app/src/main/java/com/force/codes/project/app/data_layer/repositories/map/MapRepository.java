/*
 * Created by Force Porquillo on 6/20/20 8:59 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 8:59 PM
 */

package com.force.codes.project.app.data_layer.repositories.map;

import com.force.codes.project.app.data_layer.model.GlobalData;
import com.force.codes.project.app.data_layer.model.PHData;

import java.util.List;

import io.reactivex.Flowable;

public interface MapRepository{
    Flowable <PHData> getAllPHData();

    Flowable <List <GlobalData>> getAllGlobalData();
}
