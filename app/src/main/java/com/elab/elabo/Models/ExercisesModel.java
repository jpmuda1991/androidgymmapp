package com.elab.elabo.Models;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class ExercisesModel {

    private String title;
    private String description;
    private int img;
    private String video_id;
    private String muscles;
    private String execution;


    public ExercisesModel(String title, String description, int img,String video_id,String muscles,String execution) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.video_id = video_id;
        this.muscles = muscles;
        this.execution = execution;

    }


    public String getVideo_id() {
        return video_id;
    }

    public String getMuscles() {
        return muscles;
    }

    public String getExecution() {
        return execution;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImg() {
        return img;
    }
}
