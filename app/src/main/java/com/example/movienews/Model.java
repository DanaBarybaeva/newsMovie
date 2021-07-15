package com.example.movienews;

public class Model {
    String name;


    String vote_average;


    String backgroundimage;



    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackgroundimage() {
        return backgroundimage;
    }

    public void setBackgroundimage(String backgroundimage) {
        this.backgroundimage = backgroundimage;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public Model(String name, String data, String image, String description, String backgroundimage, String vote_average) {
        this.name = name;
        this.data = data;
        this.image = image;
        this.description = description;
        this.backgroundimage = backgroundimage;
        this.vote_average = vote_average;
    }
    public Model(){}

    String data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;
}
