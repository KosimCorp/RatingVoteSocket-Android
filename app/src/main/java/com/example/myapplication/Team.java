package com.example.myapplication;

public class Team {
    private int idTeam;
    private String name;
    private int rImage;

    public Team() {}

    public Team(String name, int rImage) {
        this.name = name;
        this.rImage = rImage;
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

    public int getrImage() {
        return rImage;
    }

    public void setrImage(int rImage) {
        this.rImage = rImage;
    }
}
