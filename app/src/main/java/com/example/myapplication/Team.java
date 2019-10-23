package com.example.myapplication;

import android.graphics.Bitmap;

public class Team {
    private int idTeam;
    private String name;
    private Bitmap image;

    public Team() {}

    public Team(String name, Bitmap rImage) {
        this.name = name;
        this.image = rImage;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getrImage() {
        return image;
    }

    public void setrImage(Bitmap rImage) {
        this.image = rImage;
    }
}
