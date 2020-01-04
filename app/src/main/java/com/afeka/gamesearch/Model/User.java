package com.afeka.gamesearch.Model;

public class User {

    private String userName;
    private String password;
    private USERS role;

    public User() {
    }

    public User(String userName, String password, USERS role) {
        super();
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public USERS getRole() {
        return role;
    }

    public void setRole(USERS role) {
        this.role = role;
    }




}

