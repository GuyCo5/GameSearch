package com.afeka.gamesearch.Layout;

import com.afeka.gamesearch.Model.USERS;
import com.afeka.gamesearch.Model.User;

public class UserBoundary {

    private String userName;
    private String role;

    public UserBoundary() {
    }

    public UserBoundary(String userName, String role) {
        super();
        this.userName = userName;
        this.role = role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }


    public User toEntity(){
        User entity = new User();
        entity.setUserName(this.userName);
        if (this.role != null){
            entity.setRole(USERS.valueOf(this.role));
        }
        else{
            entity.setRole(null);
        }
        return entity;
    }
}
