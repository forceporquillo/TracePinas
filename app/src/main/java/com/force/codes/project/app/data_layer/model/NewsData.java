package com.force.codes.project.app.data_layer.model;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsData{
    @PrimaryKey()
    private int id;

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<ArticlesItem> articles;

    @SerializedName("status")
    private String status;

    public int getTotalResults(){
        return totalResults;
    }

    public List<ArticlesItem> getArticles(){
        return articles;
    }

    public String getStatus(){
        return status;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTotalResults(int totalResults){
        this.totalResults = totalResults;
    }

    public void setArticles(List<ArticlesItem> articles){
        this.articles = articles;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public int getId(){
        return id;
    }
}