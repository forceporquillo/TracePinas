/*
 * Created by Force Porquillo on 7/11/20 1:51 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/11/20 1:51 AM
 */

package com.force.codes.project.app.data_layer.model.twitter;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class TwitterMediaUrl{
    @SerializedName("media_url_https")
    @Expose
    private String mediaUrlHttps;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("display_url")
    @Expose
    private String displayUrl;

    @SerializedName("expanded_url")
    @Expose
    private String expandedUrl;

    public String getMediaUrlHttps() {
        return mediaUrlHttps;
    }

    public void setMediaUrlHttps(String mediaUrlHttps) {
        this.mediaUrlHttps = mediaUrlHttps;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public String getExpandedUrl() {
        return expandedUrl;
    }

    public void setExpandedUrl(String expandedUrl) {
        this.expandedUrl = expandedUrl;
    }
}