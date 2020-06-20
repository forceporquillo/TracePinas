package com.force.codes.project.app.presentation_layer.controller.custom.interfaces;


/*
 * Created by Force Porquillo on 6/2/20 1:49 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 12:55 PM
 *
 */

import com.force.codes.project.app.data_layer.model.CountryDetails;

public interface FragmentCallback{
    void CustomCardViewListener(int position);
    void insertOrRemoveFavorites(CountryDetails details);
}
