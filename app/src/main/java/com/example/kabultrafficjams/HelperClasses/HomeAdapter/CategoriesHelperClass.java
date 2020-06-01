package com.example.kabultrafficjams.HelperClasses.HomeAdapter;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class CategoriesHelperClass {

    int imageView;
    String textView;
    Drawable relativeLayout;

    public CategoriesHelperClass(int imageView, String textView, Drawable relativeLayout) {
        this.imageView = imageView;
        this.textView = textView;
        this.relativeLayout = relativeLayout;
    }

    public int getImage() {
        return imageView;
    }

    public String getTitle() {
        return textView;
    }

    public Drawable getGradient() {
        return relativeLayout;
    }
}
