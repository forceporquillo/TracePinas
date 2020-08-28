package com.force.codes.project.app.data_layer.model.doh_data_drop_csv;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TopRegions{

	@SerializedName("data")
	private List<PHProvinces> data;

  public TopRegions(List<PHProvinces> data) {
  }

  public void setData(List<PHProvinces> data){
		this.data = data;
	}

	public List<PHProvinces> getData(){
		return data;
	}
}