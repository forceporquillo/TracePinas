/*
 * Created by Force Porquillo on 6/19/20 2:13 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/19/20 2:13 PM
 */

package com.force.codes.project.app.data_layer.model.country;

/*
 * Created by Force Porquillo on 6/5/20 3:28 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/5/20 3:22 PM
 *
 */

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "CountryDetails")
public class CountryDetails extends BaseObservable{
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "Country")
    @SerializedName("country")
    private String country;

    @SerializedName("updated")
    @ColumnInfo(name = "Date")
    private long updated;

    @SerializedName("continent")
    @ColumnInfo(name = "Continent")
    private String continent;

    @SerializedName("cases")
    @ColumnInfo(name = "Cases")
    private int cases;

    @SerializedName("todayCases")
    @ColumnInfo(name = "New Cases")
    private int todayCases;

    @SerializedName("deaths")
    @ColumnInfo(name = "Deaths")
    private int deaths;

    @SerializedName("todayDeaths")
    @ColumnInfo(name = "New Deaths")
    private int todayDeaths;

    @SerializedName("recovered")
    @ColumnInfo(name = "Recovered")
    private int recovered;

    @SerializedName("todayRecovered")
    @ColumnInfo(name = "New Recovered")
    private int todayRecovered;

    @SerializedName("active")
    @ColumnInfo(name = "Active")
    private int active;

    @SerializedName("critical")
    @ColumnInfo(name = "Critical")
    private int critical;

    @SerializedName("casesPerOneMillion")
    @ColumnInfo(name = "Cases Per One Million")
    private double casesPerOneMillion;

    @SerializedName("deathsPerOneMillion")
    @ColumnInfo(name = "Deaths Per One Million")
    private double deathsPerOneMillion;

    @SerializedName("tests")
    @ColumnInfo(name = "Test")
    private int test;

    @SerializedName("testsPerOneMillion")
    @ColumnInfo(name = "Test Per One Million")
    private int testPerOneMillion;

    @Embedded
    private CountryInfo countryInfo;

    @Bindable
    public CountryInfo getCountryInfo(){
        return countryInfo;
    }

    public void setCountryInfo(CountryInfo countryInfo){
        this.countryInfo = countryInfo;
    }

    @NonNull
    @Bindable
    public String getCountry(){
        return country;
    }

    public void setCountry(@NonNull String country){
        this.country = country;
       // notifyPropertyChanged(BR.country);
    }

    public int getTodayRecovered(){
        return todayRecovered;
    }

    public void setTodayRecovered(int todayRecovered){
        this.todayRecovered = todayRecovered;
    }

    public long getUpdated(){
        return updated;
    }

    public void setUpdated(long updated){
        this.updated = updated;
    }

    public String getContinent(){
        return continent;
    }


    public void setContinent(String continent){
        this.continent = continent;
    }

    public int getCases(){
        return cases;
    }

    public void setCases(int cases){
        this.cases = cases;
    }

    public int getTodayCases(){
        return todayCases;
    }

    public void setTodayCases(int todayCases){
        this.todayCases = todayCases;
    }

    public int getDeaths(){
        return deaths;
    }

    public void setDeaths(int deaths){
        this.deaths = deaths;
    }

    public int getTodayDeaths(){
        return todayDeaths;
    }

    public void setTodayDeaths(int todayDeaths){
        this.todayDeaths = todayDeaths;
    }

    public int getRecovered(){
        return recovered;
    }

    public void setRecovered(int recovered){
        this.recovered = recovered;
    }

    public int getActive(){
        return active;
    }

    public void setActive(int active){
        this.active = active;
    }

    public int getCritical(){
        return critical;
    }

    public void setCritical(int critical){
        this.critical = critical;
    }

    public double getCasesPerOneMillion(){
        return casesPerOneMillion;
    }

    public void setCasesPerOneMillion(double casesPerOneMillion){
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public double getDeathsPerOneMillion(){
        return deathsPerOneMillion;
    }

    public void setDeathsPerOneMillion(double deathsPerOneMillion){
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public int getTest(){
        return test;
    }

    public void setTest(int test){
        this.test = test;
    }

    public int getTestPerOneMillion(){
        return testPerOneMillion;
    }

    public void setTestPerOneMillion(int testPerOneMillion){
        this.testPerOneMillion = testPerOneMillion;
    }
}