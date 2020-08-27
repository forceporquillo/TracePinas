package com.force.codes.project.app.data_layer.model.doh_data_drop_csv;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TopRegions{

	@SerializedName("data")
	private List<DataItem> data;

  public TopRegions(List<DataItem> data) {
  }

  public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}
}