package com.afeka.gamesearch.Layout;

public class LoginBoundary {

    private boolean validPass;
    private String userRole;

    public LoginBoundary() {
    }

    public LoginBoundary(boolean validPass,String userRole) {
        super();
        this.validPass = validPass;
        this.userRole = userRole;
    }

    public boolean isValidPass() {
        return validPass;
    }

    public void setValidPass(boolean validPass) {
        this.validPass = validPass;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
