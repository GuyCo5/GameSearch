package com.afeka.gamesearch.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.afeka.gamesearch.Layout.VideoGameBoundary;

import org.springframework.web.client.RestTemplate;

public class RestTaskPutGame extends AsyncTask<Void,Void, Void> {


    private String url;
    private RestTemplate restTemplate;
    private ProgressDialog dialog;
    private VideoGameBoundary videoGameToUpdate;


    public RestTaskPutGame(String url,VideoGameBoundary videoGameToUpdate, Activity activity) {
        this.url = url;
        this.videoGameToUpdate = videoGameToUpdate;
        restTemplate = new RestTemplate();
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        restTemplate.put(url,videoGameToUpdate);
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("loading....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
