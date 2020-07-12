/*
 * Created by Force Porquillo on 7/11/20 1:56 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/10/20 10:23 AM
 */

package com.force.codes.project.app.data_layer.model.news;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity
public class ArticlesItem extends BaseObservable{
	@PrimaryKey
	@NonNull
	@SerializedName("publishedAt")
	private String publishedAt;

	@SerializedName("author")
	private String author;

	@SerializedName("urlToImage")
	private String urlToImage;

	@SerializedName("description")
	private String description;

	@Embedded
	@SerializedName("source")
	private Source source;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("content")
	private String content;

	public void setSource(Source source){
		this.source = source;
	}

	public Source getSource(){
		return source;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt){
		this.publishedAt = publishedAt;
	}

	public String getAuthor(){
		return author;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getUrlToImage(){
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage){
		this.urlToImage = urlToImage;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	@NotNull
	public String getTitle(){
		return title;
	}

	public void setTitle(@NotNull String title){
		this.title = title;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}
}