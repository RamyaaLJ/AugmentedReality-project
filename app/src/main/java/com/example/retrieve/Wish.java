package com.example.retrieve;

import android.net.Uri;
public class Wish {
    int id;
    String name;
    String price;
    String img;
    public Wish(){

    }
    public Wish(int id, String name, String img,String price) {
        this.img=img;
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

