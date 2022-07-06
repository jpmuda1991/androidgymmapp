package com.elab.elabo.Application;

import android.app.Application;

import com.elab.elabo.Models.CategoriesModel;
import com.elab.elabo.Models.ExercisesModel;
import com.elab.elabo.Models.ImagePickerModel;

public class BodyApplication extends Application {

    private static CategoriesModel categoriesModel = null;
    private static ExercisesModel exercisesModel = null;
    private static ImagePickerModel imagePickerModel = null;
    private static  int pos = -1;


    public static ImagePickerModel getImagePickerModel() {
        return imagePickerModel;
    }

    public static void setImagePickerModel(ImagePickerModel imagePickerModel) {
        BodyApplication.imagePickerModel = imagePickerModel;
    }

    public static int getPos() {
        return pos;
    }

    public static void setPos(int pos) {
        BodyApplication.pos = pos;
    }

    public static ExercisesModel getExercisesModel() {
        return exercisesModel;
    }

    public static void setExercisesModel(ExercisesModel exercisesModel) {
        BodyApplication.exercisesModel = exercisesModel;
    }

    public static CategoriesModel getCategoriesModel() {
        return categoriesModel;
    }

    public static void setCategoriesModel(CategoriesModel categoriesModel) {
        BodyApplication.categoriesModel = categoriesModel;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
