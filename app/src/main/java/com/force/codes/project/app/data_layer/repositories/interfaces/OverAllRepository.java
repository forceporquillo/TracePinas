/*
 * Created by Force Porquillo on 7/26/20 6:26 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/26/20 6:26 PM
 */

package com.force.codes.project.app.data_layer.repositories.interfaces;

import com.force.codes.project.app.data_layer.model.overall.TotalByDate;
import io.reactivex.Flowable;
import java.util.List;

public interface OverAllRepository {
  Flowable<List<TotalByDate>> getTotalByDateFromNetwork(String url);
}
