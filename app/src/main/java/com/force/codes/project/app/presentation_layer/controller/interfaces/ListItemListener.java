/*
 * Created by Force Porquillo on 8/8/20 8:20 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/8/20 8:20 PM
 */

package com.force.codes.project.app.presentation_layer.controller.interfaces;

import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import java.util.List;

public interface ListItemListener {
  void getResult(List<CountryDetails> details);
}
