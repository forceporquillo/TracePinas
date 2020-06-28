/*
 * Created by Force Porquillo on 6/19/20 2:13 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/19/20 2:13 PM
 */

package com.force.codes.project.app.data_layer.model;

/*
 * Created by Force Porquillo on 6/5/20 3:28 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/5/20 3:22 PM
 *
 */

import com.force.codes.project.app.presentation_layer.controller.custom.utils.StringUtils;
import com.google.gson.annotations.SerializedName;

public class WorldData extends StringUtils{

    @SerializedName("recovered")
    private int recovered;

    @SerializedName("cases")
    private int cases;

    @SerializedName("tests")
    private int tests;

    @SerializedName("critical")
    private int critical;

    @SerializedName("todayRecovered")
    private int todayRecovered;

    @SerializedName("active")
    private int active;

    @SerializedName("updated")
    private long updated;

    @SerializedName("deaths")
    private int deaths;

    @SerializedName("todayCases")
    private int todayCases;

    @SerializedName("todayDeaths")
    private int todayDeaths;

    public String getRecovered(){
        return formatNumber(String.valueOf(recovered));
    }

    public void setRecovered(int recovered){
        this.recovered = recovered;
    }

    public String getCases(){
        return formatNumber(String.valueOf(cases));
    }

    public void setCases(int cases){
        this.cases = cases;
    }

    public int getTests(){
        return tests;
    }

    public void setTests(int tests){
        this.tests = tests;
    }

    public int getCritical(){
        return critical;
    }

    public void setCritical(int critical){
        this.critical = critical;
    }

    public String getTodayRecovered(){
        return formatNumber(String.valueOf(todayRecovered));
    }

    public void setTodayRecovered(int todayRecovered){
        this.todayRecovered = todayRecovered;
    }

    public int getActive(){
        return active;
    }

    public void setActive(int active){
        this.active = active;
    }

    public String getUpdated(){
        return getDate(updated);
    }

    public void setUpdated(long updated){
        this.updated = updated;
    }

    public String getDeaths(){
        return formatNumber(String.valueOf(deaths));
    }

    public void setDeaths(int deaths){
        this.deaths = deaths;
    }

    public String getTodayCases(){
        return formatNumber(String.valueOf(todayCases));
    }

    public void setTodayCases(int todayCases){
        this.todayCases = todayCases;
    }

    public String getTodayDeaths(){
        return formatNumber(String.valueOf(todayDeaths));
    }

    public void setTodayDeaths(int todayDeaths){
        this.todayDeaths = todayDeaths;
    }
}