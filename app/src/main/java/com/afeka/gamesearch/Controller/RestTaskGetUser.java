package com.afeka.gamesearch.Controller;

import android.os.AsyncTask;
import com.afeka.gamesearch.Layout.LoginBoundary;
import org.springframework.web.client.RestTemplate;

public class RestTaskGetUser extends AsyncTask<Void,Void, LoginBoundary> {

    private String url;
    private RestTemplate restTemplate;

    public RestTaskGetUser(String url) {
        this.url = url;
        restTemplate = new RestTemplate();
    }

    @Override
    protected LoginBoundary doInBackground(Void... voids) {
        LoginBoundary lb = restTemplate.getForObject(url, LoginBoundary.class);
        return lb;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(LoginBoundary userResponseEntity) {
        super.onPostExecute(userResponseEntity);
    }
}
