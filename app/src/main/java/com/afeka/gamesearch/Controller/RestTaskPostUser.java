package com.afeka.gamesearch.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.afeka.gamesearch.Layout.UserBoundary;

import org.springframework.web.client.RestTemplate;

public class RestTaskPostUser extends AsyncTask<Void,Void, UserBoundary> {

    private String url;
    private RestTemplate restTemplate;
    private ProgressDialog dialog;
    private UserBoundary registerUser;

    public RestTaskPostUser(String url,UserBoundary user, Activity activity) {
        this.url = url;
        this.registerUser = user;
        restTemplate = new RestTemplate();
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected UserBoundary doInBackground(Void... voids) {
        return restTemplate.postForObject(url,registerUser,UserBoundary.class);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("loading....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }
}
