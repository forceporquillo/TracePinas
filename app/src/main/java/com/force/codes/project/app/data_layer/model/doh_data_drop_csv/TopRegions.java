package com.force.codes.project.app.data_layer.model.doh_data_drop_csv;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TopRegions{

	@SerializedName("data")
	private List<PHProvinces> provinces;

  public TopRegions(List<PHProvinces> provinces) {
  }

  public void setProvinces(List<PHProvinces> provinces){
		this.provinces = provinces;
	}

	public List<PHProvinces> getProvinces(){
		return provinces;
	}
}