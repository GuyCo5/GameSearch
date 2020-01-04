package com.afeka.gamesearch.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import org.springframework.web.client.RestTemplate;

public class RestTaskDeleteGame  extends AsyncTask<Void,Void,Void> {

    private String url;
    private RestTemplate restTemplate;
    private ProgressDialog dialog;

    public RestTaskDeleteGame(String url, Activity activity) {
        this.url = url;
        restTemplate = new RestTemplate();
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        restTemplate.delete(url);
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
