package com.example.retrieve;
import android.net.Uri;
public class Cart {
    int id;
    String name,date,time;
    String price;
    String img;
    public Cart(){

    }
    public Cart(int id,String price, String name, String img,String date,String time) {
        this.img=img;
        this.id = id;
        this.name = name;
        this.date=date;
        this.time=time;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}

