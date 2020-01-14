package com.afeka.gamesearch.Controller;

import android.os.AsyncTask;
import org.springframework.web.client.RestTemplate;

public class RestTaskDeleteGame  extends AsyncTask<Void,Void,Void> {

    private String url;
    private RestTemplate restTemplate;

    public RestTaskDeleteGame(String url) {
        this.url = url;
        restTemplate = new RestTemplate();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        restTemplate.delete(url);
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
