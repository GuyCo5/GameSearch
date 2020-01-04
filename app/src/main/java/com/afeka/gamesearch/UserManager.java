package com.afeka.gamesearch;


import android.content.Context;
import android.content.SharedPreferences;

import com.afeka.gamesearch.Model.USERS;

public class UserManager {

    private String userName;
    private String password;
    private USERS userType;
    private SharedPreferences sharedPref;
    private Context context;
    String defaultValue;

    public UserManager(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        defaultValue = context.getString(R.string.defaultKeyValue);
    }

    public String getUserName() {
        if (userName == null || userName == "")
            userName = sharedPref.getString(context.getString(R.string.username_key),defaultValue);
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.username_key),userName);
        editor.apply();
    }

    public String getPassword() {
        if (password == null || password == ""){
            password = sharedPref.getString(context.getString(R.string.password_key),defaultValue);
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.password_key),password);
        editor.apply();
    }

    public USERS getUserType() {
        if (userType == null){
            userType = USERS.valueOf(sharedPref.getString(context.getString(R.string.userType_key),defaultValue));
        }
        return userType;
    }

    public void setUserType(USERS userType) {
        this.userType = userType;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.userType_key),userType.toString());
        editor.apply();
    }
}