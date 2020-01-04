package com.afeka.gamesearch.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.afeka.gamesearch.Layout.LoginBoundary;
import com.afeka.gamesearch.Layout.UserBoundary;
import com.afeka.gamesearch.Model.User;

import org.springframework.web.client.RestTemplate;

public class RestTaskGetUser extends AsyncTask<Void,Void, LoginBoundary> {

    private String url;
    private RestTemplate restTemplate;
    private ProgressDialog dialog;


    public RestTaskGetUser(String url, Activity activity) {
        this.url = url;
        restTemplate = new RestTemplate();
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected LoginBoundary doInBackground(Void... voids) {
        LoginBoundary lb = restTemplate.getForObject(url, LoginBoundary.class);
        return lb;
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
    protected void onPostExecute(LoginBoundary userResponseEntity) {
        super.onPostExecute(userResponseEntity);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
