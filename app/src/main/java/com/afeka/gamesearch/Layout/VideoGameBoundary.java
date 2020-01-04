package com.afeka.gamesearch.Layout;

import com.afeka.gamesearch.Model.GENRES;
import com.afeka.gamesearch.Model.VideoGame;

public class VideoGameBoundary {
    private String gameName;
    private String genre;
    private int deploymentYear;
    private String company;


    public VideoGameBoundary() {
    }

    public VideoGameBoundary(String gameName, String genre, int deploymentYear, String company) {
        super();
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
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
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


    public VideoGame toEntity() {
        VideoGame entity = new VideoGame();
        entity.setGameName(this.gameName);
        entity.setCompany(this.company);
        entity.setDeploymentYear(this.deploymentYear);
        if (this.genre != null){
            entity.setGenre(GENRES.valueOf(this.genre));
        }
        else{
            entity.setGenre(null);
        }
        return entity;
    }
}

