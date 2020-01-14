package com.afeka.gamesearch.Controller;


import android.content.Context;
import com.afeka.gamesearch.Layout.LoginBoundary;
import com.afeka.gamesearch.Layout.UserBoundary;
import com.afeka.gamesearch.Model.USERS;
import com.afeka.gamesearch.Model.User;
import com.afeka.gamesearch.R;
import java.util.concurrent.ExecutionException;

public class UserRestIntegration {

    private String baseUrl;
    private OnRestInteractionListener binder;

    public UserRestIntegration(OnRestInteractionListener binder, Context context) {
        this.baseUrl = context.getString(R.string.server_url);
        this.binder = binder;
    }

    public void registerUser(User user){
        UserBoundary userBoundary = new UserBoundary(user);
        String urlExtra = "/register";
        try {
            userBoundary = new RestTaskPostUser(baseUrl+urlExtra,userBoundary).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User returnUser = userBoundary.toEntity();
        binder.onRestRegisterComplete(returnUser);
    }

    public void loginUser(User user){
        LoginBoundary lb = new LoginBoundary();
        String urlExtra = "/login/" + user.getUserName() + "/" + user.getPassword();
        try {
            lb = new RestTaskGetUser(baseUrl + urlExtra).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (lb.isValidPass()){
            user.setRole(USERS.valueOf(lb.getUserRole()));
            binder.onRestLoginComplete(user);
        } else{
            binder.onRestLoginFail();
        }
    }

    public interface OnRestInteractionListener {
        void onRestRegisterComplete(User user);
        void onRestLoginComplete(User user);
        void onRestLoginFail();
    }
}
