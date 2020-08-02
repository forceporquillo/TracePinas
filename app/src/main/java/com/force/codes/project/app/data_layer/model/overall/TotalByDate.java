/*
 * Created by Force Porquillo on 8/1/20 10:49 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/30/20 4:49 PM
 */

package com.force.codes.project.app.data_layer.model.overall;

import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

public class TotalByDate {
  @PrimaryKey
  @SerializedName("data")
  private Data data;

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }
}