package com.force.codes.project.app.data_layer.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PrimarySelected {
  @PrimaryKey
  @NonNull
  private int id;

  private String countryKey;

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setCountryKey(String countryKey) {
    this.countryKey = countryKey;
  }

  public String getCountryKey() {
    return countryKey;
  }
}
