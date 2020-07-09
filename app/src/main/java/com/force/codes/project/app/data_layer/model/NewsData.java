package com.force.codes.project.app.data_layer.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import com.force.codes.project.app.data_layer.TypeConverter.ArticleItemsConverter;
import com.google.gson.annotations.SerializedName;

@Entity
public class NewsData{
	@PrimaryKey(autoGenerate = true)
	private int id;

	@SerializedName("totalResults")
	private int totalResults;

	@SerializedName("articles")
	@TypeConverters(ArticleItemsConverter.class)
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