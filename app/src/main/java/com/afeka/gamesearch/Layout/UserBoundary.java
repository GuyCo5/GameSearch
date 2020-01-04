package com.afeka.gamesearch.Layout;

import com.afeka.gamesearch.Model.USERS;
import com.afeka.gamesearch.Model.User;

public class UserBoundary {

    private String userName;
    private String password;
    private String role;


    public UserBoundary() {
    }

    public UserBoundary(String userName, String password, String role) {
        super();
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public UserBoundary (User user){
        super();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.role = user.getRole().toString();
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public User toEntity(){
        User entity = new User();
        entity.setUserName(this.userName);
        entity.setPassword(this.password);
        if (this.role != null){
            entity.setRole(USERS.valueOf(this.role));
        }
        else{
            entity.setRole(null);
        }
        return entity;
    }
}
