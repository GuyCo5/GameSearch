package com.afeka.gamesearch.Controller;

import android.app.Activity;
import android.os.AsyncTask;
import com.afeka.gamesearch.Layout.UserBoundary;

import org.springframework.web.client.RestTemplate;

public class RestTaskPostUser extends AsyncTask<Void,Void, UserBoundary> {

    private String url;
    private RestTemplate restTemplate;
    private UserBoundary registerUser;

    public RestTaskPostUser(String url,UserBoundary user) {
        this.url = url;
        this.registerUser = user;
        restTemplate = new RestTemplate();
    }

    @Override
    protected UserBoundary doInBackground(Void... voids) {
        return restTemplate.postForObject(url,registerUser,UserBoundary.class);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
