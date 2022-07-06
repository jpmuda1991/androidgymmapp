package com.elab.elabo.Models;

import java.util.ArrayList;

public class ImagePickerModel {


    private int img;
    private boolean isChecked;


    public ImagePickerModel() {
    }

    public ImagePickerModel(int img, boolean isChecked) {
        this.img = img;
        this.isChecked = isChecked;
    }


    public int getImg() {
        return img;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
