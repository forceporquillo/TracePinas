/*
 * Created by Force Porquillo on 6/26/20 9:59 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/26/20 9:59 PM
 */

package com.force.codes.project.app.data_layer.model.world;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class GlobalData{
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "ID")
    @SerializedName("uid")
    private int uid;
    @SerializedName("lastUpdate")
    @Expose
    private long lastUpdate;
    @SerializedName("lat")
    @Expose
    private double latitude;
    @SerializedName("long")
    @Expose
    private double longitude;
    @SerializedName("confirmed")
    @Expose
    private int confirmed;
    @SerializedName("recovered")
    @Expose
    private int recovered;
    @SerializedName("deaths")
    @Expose
    private int deaths;
    @SerializedName("active")
    @Expose
    private int active;
    @SerializedName("combinedKey")
    @Expose
    private String combinedKey;
    @SerializedName("incidentRate")
    @Expose
    private double incidentRate;

    public int getUid(){
        return uid;
    }

    public void setUid(int uid){
        this.uid = uid;
    }

    public long getLastUpdate(){
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    public double getLatitude(){
        return latitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public int getConfirmed(){
        return confirmed;
    }

    public void setConfirmed(int confirmed){
        this.confirmed = confirmed;
    }

    public int getRecovered(){
        return recovered;
    }

    public void setRecovered(int recovered){
        this.recovered = recovered;
    }

    public int getDeaths(){
        return deaths;
    }

    public void setDeaths(int deaths){
        this.deaths = deaths;
    }

    public int getActive(){
        return active;
    }

    public void setActive(int active){
        this.active = active;
    }

    public String getCombinedKey(){
        return combinedKey;
    }

    public void setCombinedKey(String combinedKey){
        this.combinedKey = combinedKey;
    }

    public double getIncidentRate(){
        return incidentRate;
    }

    public void setIncidentRate(double incidentRate){
        this.incidentRate = incidentRate;
    }

}
