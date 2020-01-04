package com.afeka.gamesearch.Model;

public class VideoGame {

    private String gameName;
    private GENRES genre;
    private int deploymentYear;
    private String company;

//    private String publisher;
//    private String developer;
//    private String series;
//    private String info;
//    private int image;


    public VideoGame() {
    }

    public VideoGame(String gameName, GENRES genre, int deploymentYear, String company) {
        this.gameName = gameName;
        this.genre = genre;
        this.deploymentYear = deploymentYear;
        this.company = company;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public GENRES getGenre() {
        return genre;
    }

    public void setGenre(GENRES genre) {
        this.genre = genre;
    }

    public int getDeploymentYear() {
        return deploymentYear;
    }

    public void setDeploymentYear(int deploymentYear) {
        this.deploymentYear = deploymentYear;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
