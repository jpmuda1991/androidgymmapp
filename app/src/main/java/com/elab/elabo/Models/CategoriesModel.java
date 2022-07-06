package com.elab.elabo.Models;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class CategoriesModel {

    private int id;
    private String title;
    private int img;
    private ArrayList<ExercisesModel> exercisesModelArrayList;


    public CategoriesModel() {
    }

    public CategoriesModel(int id, String title, int img, ArrayList<ExercisesModel> exercisesModelArrayList) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.exercisesModelArrayList = exercisesModelArrayList;
    }

    public CategoriesModel(String title, int img, ArrayList<ExercisesModel> exercisesModelArrayList) {
        this.title = title;
        this.img = img;
        this.exercisesModelArrayList = exercisesModelArrayList;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setExercisesModelArrayList(ArrayList<ExercisesModel> exercisesModelArrayList) {
        this.exercisesModelArrayList = exercisesModelArrayList;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getImg() {
        return img;
    }

    public ArrayList<ExercisesModel> getExercisesModelArrayList() {
        return exercisesModelArrayList;
    }
}
