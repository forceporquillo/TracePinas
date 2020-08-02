/*
 * Created by Force Porquillo on 6/20/20 8:59 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 8:59 PM
 */

package com.force.codes.project.app.data_layer.repositories.interfaces;

import com.force.codes.project.app.data_layer.model.map_data.LocalData;
import com.force.codes.project.app.data_layer.model.world.GlobalData;
import io.reactivex.Flowable;
import java.util.List;

public interface MapRepository {
  Flowable<LocalData> getAllPHData();

  Flowable<List<GlobalData>> getAllGlobalData();
}
