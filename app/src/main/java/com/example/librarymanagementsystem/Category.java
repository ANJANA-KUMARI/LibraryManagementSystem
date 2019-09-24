package com.example.librarymanagementsystem;

import android.widget.ImageView;

public class Category {

    public int id;
    public String image;
    public String text;

    public Category(){

    }

    public Category(String categotyName, String image){
        this.text = categotyName;
        this.image =image;
    }

}
