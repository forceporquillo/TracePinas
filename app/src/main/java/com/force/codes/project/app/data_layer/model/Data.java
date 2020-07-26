package com.force.codes.project.app.data_layer.model;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class Data{
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

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setConfirmedDiff(int confirmedDiff){
		this.confirmedDiff = confirmedDiff;
	}

	public int getConfirmedDiff(){
		return confirmedDiff;
	}

	public void setActiveDiff(int activeDiff){
		this.activeDiff = activeDiff;
	}

	public int getActiveDiff(){
		return activeDiff;
	}

	public void setDeathsDiff(int deathsDiff){
		this.deathsDiff = deathsDiff;
	}

	public int getDeathsDiff(){
		return deathsDiff;
	}

	public void setRecovered(int recovered){
		this.recovered = recovered;
	}

	public int getRecovered(){
		return recovered;
	}

	public void setRecoveredDiff(int recoveredDiff){
		this.recoveredDiff = recoveredDiff;
	}

	public int getRecoveredDiff(){
		return recoveredDiff;
	}

	public void setFatalityRate(double fatalityRate){
		this.fatalityRate = fatalityRate;
	}

	public double getFatalityRate(){
		return fatalityRate;
	}

	public void setLastUpdate(String lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdate(){
		return lastUpdate;
	}

	public void setActive(int active){
		this.active = active;
	}

	public int getActive(){
		return active;
	}

	public void setConfirmed(int confirmed){
		this.confirmed = confirmed;
	}

	public int getConfirmed(){
		return confirmed;
	}

	public void setDeaths(int deaths){
		this.deaths = deaths;
	}

	public int getDeaths(){
		return deaths;
	}
}