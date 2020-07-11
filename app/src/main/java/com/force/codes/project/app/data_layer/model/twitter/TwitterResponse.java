/*
 * Created by Force Porquillo on 7/11/20 1:50 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/11/20 1:50 AM
 */

package com.force.codes.project.app.data_layer.model.twitter;

import androidx.annotation.Nullable;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class TwitterResponse {
    @PrimaryKey
    @SerializedName("id_str")
    @Expose
    private String id;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("full_text")
    @Expose
    private String fullText;

    @Embedded
    @SerializedName("entities")
    @Expose
    @Nullable
    private Entities entities;

    @SerializedName("retweet_count")
    @Expose
    private int retweetCount;

    @SerializedName("favorite_count")
    @Expose
    private int favoriteCount;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    @org.jetbrains.annotations.Nullable
    public Entities getEntities() {
        return entities;
    }

    public void setEntities(@org.jetbrains.annotations.Nullable Entities entities) {
        this.entities = entities;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }
}