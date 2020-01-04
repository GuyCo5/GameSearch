package com.afeka.gamesearch.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.afeka.gamesearch.Layout.VideoGameBoundary;
import org.springframework.web.client.RestTemplate;

public class RestTaskPostGame extends AsyncTask<Void,Void, VideoGameBoundary> {

    private String url;
    private RestTemplate restTemplate;
    private ProgressDialog dialog;
    private VideoGameBoundary videoGameToAdd;

    public RestTaskPostGame(String url,VideoGameBoundary videoGameToAdd, Activity activity) {
        this.url = url;
        this.videoGameToAdd = videoGameToAdd;
        restTemplate = new RestTemplate();
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected VideoGameBoundary doInBackground(Void... voids) {
        return restTemplate.postForObject(url,videoGameToAdd,VideoGameBoundary.class);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("loading....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onPostExecute(VideoGameBoundary videoGame) {
        super.onPostExecute(videoGame);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
