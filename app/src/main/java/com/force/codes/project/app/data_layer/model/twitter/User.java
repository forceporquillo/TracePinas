package com.force.codes.project.app.data_layer.model.twitter;

import androidx.room.Entity;
import com.google.gson.annotations.SerializedName;

@Entity
public class User {

  @SerializedName("screen_name")
  private String screenName;

  @SerializedName("id_str")
  private String userIDStr;

  @SerializedName("name")
  private String name;

  @SerializedName("location")
  private String location;

  public String getScreenName() {
    return screenName;
  }

  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getUserIDStr() {
    return userIDStr;
  }

  public void setUserIDStr(String userIDStr) {
    this.userIDStr = userIDStr;
  }
}