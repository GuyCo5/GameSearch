package com.afeka.gamesearch.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.afeka.gamesearch.Layout.VideoGameBoundary;

import org.springframework.web.client.RestTemplate;

public class RestTaskPutGame extends AsyncTask<Void,Void, Void> {

    private String url;
    private RestTemplate restTemplate;
    private VideoGameBoundary videoGameToUpdate;

    public RestTaskPutGame(String url,VideoGameBoundary videoGameToUpdate) {
        this.url = url;
        this.videoGameToUpdate = videoGameToUpdate;
        restTemplate = new RestTemplate();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        restTemplate.put(url,videoGameToUpdate);
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
