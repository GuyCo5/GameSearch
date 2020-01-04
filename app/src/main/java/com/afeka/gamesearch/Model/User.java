package com.afeka.gamesearch.Model;

public class User {

    private String userName;
    private USERS role;

    public User() {
    }

    public User(String userName, USERS role) {
        super();
        this.userName = userName;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public USERS getRole() {
        return role;
    }

    public void setRole(USERS role) {
        this.role = role;
    }




}

