/*
 * Created by Force Porquillo on 8/1/20 10:49 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/31/20 9:49 AM
 */

package com.force.codes.project.app.data_layer.model.overall;

import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

public class Data {
  @PrimaryKey
  @SerializedName("date")
  private String date;

  @SerializedName("confirmed_diff")
  private int confirmedDiff;

  @SerializedName("active_diff")
  private int activeDiff;

  @SerializedName("deaths_diff")
  private int deathsDiff;

  @SerializedName("recovered")
  private int recovered;

  @SerializedName("recovered_diff")
  private int recoveredDiff;

  @SerializedName("fatality_rate")
  private double fatalityRate;

  @SerializedName("last_update")
  private String lastUpdate;

  @SerializedName("active")
  private int active;

  @SerializedName("confirmed")
  private int confirmed;

  @SerializedName("deaths")
  private int deaths;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getConfirmedDiff() {
    return confirmedDiff;
  }

  public void setConfirmedDiff(int confirmedDiff) {
    this.confirmedDiff = confirmedDiff;
  }

  public int getActiveDiff() {
    return activeDiff;
  }

  public void setActiveDiff(int activeDiff) {
    this.activeDiff = activeDiff;
  }

  public int getDeathsDiff() {
    return deathsDiff;
  }

  public void setDeathsDiff(int deathsDiff) {
    this.deathsDiff = deathsDiff;
  }

  public int getRecovered() {
    return recovered;
  }

  public void setRecovered(int recovered) {
    this.recovered = recovered;
  }

  public int getRecoveredDiff() {
    return recoveredDiff;
  }

  public void setRecoveredDiff(int recoveredDiff) {
    this.recoveredDiff = recoveredDiff;
  }

  public double getFatalityRate() {
    return fatalityRate;
  }

  public void setFatalityRate(double fatalityRate) {
    this.fatalityRate = fatalityRate;
  }

  public String getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(String lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
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
}