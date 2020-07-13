/*
 * Created by Force Porquillo on 7/6/20 2:19 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/6/20 2:19 AM
 */

package com.force.codes.project.app.presentation_layer.controller.custom.model;

public class Group {
    private String groupTitle;

    public Group() {
    }

    public Group(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

}