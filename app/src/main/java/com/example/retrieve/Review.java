package com.example.retrieve;

public class Review {
    int id;
    String title;
    String link;

    Review(){}

    Review(int id, String title, String link){
        this.id = id;
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public int getId(){ return  id;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setId(int id) {
        this.id = id;
    }
}
