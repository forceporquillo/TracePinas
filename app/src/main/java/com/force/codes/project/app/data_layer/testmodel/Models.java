/*
 * Created by Force Porquillo on 7/6/20 2:14 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/6/20 2:14 AM
 */

package com.force.codes.project.app.data_layer.testmodel;

public class Models{
    private String title;
    private String date;
    private String thumbnail;

    public Models(String title, String date, String thumbnail){
        this.title = title;
        this.date = date;
        this.thumbnail = thumbnail;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getThumbnail(){
        return thumbnail;
    }

    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }
}
