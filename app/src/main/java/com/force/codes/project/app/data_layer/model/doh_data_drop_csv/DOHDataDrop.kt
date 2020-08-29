package com.force.codes.project.app.data_layer.model.doh_data_drop_csv

import com.google.gson.annotations.SerializedName

class DOHDataDrop {
  @SerializedName("data") lateinit var data: List<DataFromDayOne>
}