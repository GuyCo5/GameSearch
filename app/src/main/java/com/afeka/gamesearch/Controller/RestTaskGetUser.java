package com.afeka.gamesearch.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.afeka.gamesearch.Layout.UserBoundary;
import com.afeka.gamesearch.Model.User;

import org.springframework.web.client.RestTemplate;

public class RestTaskGetUser  extends AsyncTask<String,Void, User> {

    private String url;
    private RestTemplate restTemplate;
    private ProgressDialog dialog;
//    private Activity activity;

    public RestTaskGetUser(String url, Activity activity) {
        this.url = url;
//        this.activity = activity;
        restTemplate = new RestTemplate();
        dialog = new ProgressDialog(activity);

    }

    @Override
    protected User doInBackground(String... strings) {
        UserBoundary userBoundary = restTemplate.getForObject(url, UserBoundary.class);
        return userBoundary.toEntity();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("loading....");
        dialog.show();
        // dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onPostExecute(User userResponseEntity) {
        super.onPostExecute(userResponseEntity);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

    }


}
