/*
 * Created by Force Porquillo on 7/6/20 2:19 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/6/20 2:19 AM
 */

package com.force.codes.project.app.presentation_layer.controller.model;

public class Group {
    private String groupTitle;
    private boolean spinner;

    public Group() {
    }

    public Group(String title, boolean spinner) {
        this.groupTitle = title;
        this.spinner = spinner;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public boolean isSpinner(){
        return spinner;
    }

    public void setSpinner(boolean spinner){
        this.spinner = spinner;
    }
}