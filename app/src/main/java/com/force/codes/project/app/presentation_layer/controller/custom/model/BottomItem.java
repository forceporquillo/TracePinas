/*
 * Created by Force Porquillo on 6/19/20 12:10 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/19/20 12:10 AM
 */

package com.force.codes.project.app.presentation_layer.controller.custom.model;

public class BottomItem{
    private int itemId;
    private int itemIconId;
    private int itemFillIconId;
    private String itemTitle;

    public BottomItem(int itemId, String itemTitle, int itemIconId, int itemFillIconId){
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemIconId = itemIconId;
        this.itemFillIconId = itemFillIconId;
    }

    public int getItemId(){
        return itemId;
    }

    public void setItemId(int itemId){
        this.itemId = itemId;
    }

    public int getItemIconId(){
        return itemIconId;
    }

    public void setItemIconId(int itemIconId){
        this.itemIconId = itemIconId;
    }

    public int getItemFillIconId(){
        return itemFillIconId;
    }

    public void setItemFillIconId(int itemFillIconId){
        this.itemFillIconId = itemFillIconId;
    }

    public String getItemTitle(){
        return itemTitle;
    }

    public void setItemTitle(String itemTitle){
        this.itemTitle = itemTitle;
    }
}
