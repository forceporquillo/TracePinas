package com.force.codes.project.app.presentation_layer.controller.support;

/*
 * Created by Force Porquillo on 6/9/20 2:14 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

import android.os.AsyncTask;

import com.force.codes.project.app.model.CountryDetails;

import java.util.ArrayList;

public class AdapterAsyncTask extends AsyncTask<Void, Void, Void>{

    ArrayList<CountryDetails> list;

    public AdapterAsyncTask(){

    }

    @Override
    protected Void doInBackground(Void... voids){
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);
    }
}
