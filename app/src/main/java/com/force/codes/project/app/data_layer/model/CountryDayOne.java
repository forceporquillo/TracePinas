/*
 * Created by Force Porquillo on 8/7/20 7:42 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/7/20 7:42 PM
 */

package com.force.codes.project.app.data_layer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryDayOne {

  @SerializedName("Country")
  @Expose
  private String country;
  @SerializedName("Confirmed")
  @Expose
  private int confirmed;
  @SerializedName("Deaths")
  @Expose
  private int deaths;
  @SerializedName("Recovered")
  @Expose
  private int recovered;
  @SerializedName("Active")
  @Expose
  private int active;
  @SerializedName("Date")
  @Expose
  private String date;

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getConfirmed() {
    return confirmed;
  }

  public void setConfirmed(int confirmed) {
    this.confirmed = confirmed;
  }

  public int getDeaths() {
    return deaths;
  }

  public void setDeaths(int deaths) {
    this.deaths = deaths;
  }

  public int getRecovered() {
    return recovered;
  }

  public void setRecovered(int recovered) {
    this.recovered = recovered;
  }

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

}