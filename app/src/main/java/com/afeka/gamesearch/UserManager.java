package com.afeka.gamesearch;

import android.content.Context;
import android.content.SharedPreferences;
import com.afeka.gamesearch.Model.USERS;
import com.afeka.gamesearch.Model.User;

public class UserManager {


    private SharedPreferences sharedPref;
    private Context context;
    private String defaultValue;

    public UserManager(Context context) {
        this.context = context;
        sharedPref = context.getApplicationContext().getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        defaultValue = context.getString(R.string.defaultKeyValue);

    }

    public String getUserName() {
        return sharedPref.getString(context.getString(R.string.username_key),defaultValue);
    }

    public void setUserName(String userName) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.username_key),userName);
        editor.apply();
    }

    public String getPassword() {
        return sharedPref.getString(context.getString(R.string.password_key),defaultValue);
    }

    public void setPassword(String password) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.password_key),password);
        editor.apply();
    }

    public USERS getUserType() {
        String type = sharedPref.getString(context.getString(R.string.userType_key),defaultValue);
        if (!type.trim().equals(""))
            return USERS.valueOf(type);
        else {
            return USERS.INVALID;
        }
    }

    public void setUserType(USERS userType) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.userType_key),userType.toString());
        editor.apply();
    }

    public void updateFullUser (User user){
        setUserName(user.getUserName());
        setPassword(user.getPassword());
        setUserType(user.getRole());
    }

    public User getFullUser (){
        User rv = new User();
        rv.setUserName(this.getUserName());
        rv.setPassword(this.getPassword());
        rv.setRole(this.getUserType());
        return rv;
    }

    public void clearUser(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }
}