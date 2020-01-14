package com.afeka.gamesearch.Controller;

import android.os.AsyncTask;
import com.afeka.gamesearch.Layout.VideoGameBoundary;
import org.springframework.web.client.RestTemplate;

public class RestTaskPostGame extends AsyncTask<Void,Void, VideoGameBoundary> {

    private String url;
    private RestTemplate restTemplate;
    private VideoGameBoundary videoGameToAdd;

    public RestTaskPostGame(String url,VideoGameBoundary videoGameToAdd) {
        this.url = url;
        this.videoGameToAdd = videoGameToAdd;
        restTemplate = new RestTemplate();
    }

    @Override
    protected VideoGameBoundary doInBackground(Void... voids) {
        return restTemplate.postForObject(url,videoGameToAdd,VideoGameBoundary.class);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(VideoGameBoundary videoGame) {
        super.onPostExecute(videoGame);
    }
}
