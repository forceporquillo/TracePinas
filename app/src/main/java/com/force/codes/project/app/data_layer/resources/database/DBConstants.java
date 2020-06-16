package com.force.codes.project.app.data_layer.resources.database;

/*
 * Created by Force Porquillo on 6/2/20 6:24 AM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:18 AM
 *
 */

public class DBConstants{
    public static final String QUERY_ALL_DATA = "SELECT * FROM CountryDetails";
    public static final String QUERY_FAVORITE_COUNTRY = "SELECT FROM CountryDetails LIMIT 12";
    public static final String DELETE_FAVORITES = "DELETE FROM CountryDetails WHERE country =:country_id";
}
