package com.example.retrieve;
import android.net.Uri;
public class LampData {
    int id;
    String name;
    Long price;
    String img;
    String warranty;
    String offer;
    String save;
    public LampData(){

    }
    public LampData(int id, String name, Long price,String img,String warranty,String offer,String save) {
        this.img=img;
        this.id = id;
        this.name = name;
        this.price = price;
        this.warranty=warranty;
        this.offer=offer;
        this.save=save;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
