package com.afeka.gamesearch.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.afeka.gamesearch.Layout.VideoGameBoundary;
import com.afeka.gamesearch.Model.VideoGame;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;

public class RestTaskGetGames extends AsyncTask<String,Void, ArrayList<VideoGame>> {

    private String url;
    private RestTemplate restTemplate;
    private ProgressDialog dialog;
//    private Activity activity;

    public RestTaskGetGames(String url, Activity activity) {
        this.url = url;
//        this.activity = activity;
        restTemplate = new RestTemplate();
        dialog = new ProgressDialog(activity);

    }

    @Override
    protected ArrayList<VideoGame> doInBackground(String... strings) {
        ArrayList<VideoGame> videoGameList = new ArrayList<>();
        VideoGameBoundary[] list = restTemplate.getForObject(url,VideoGameBoundary[].class);
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                videoGameList.add(list[i].toEntity());
            }
        }
        return videoGameList;
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
    protected void onPostExecute(ArrayList<VideoGame> videoGameResponseEntity) {
        super.onPostExecute(videoGameResponseEntity);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

    }


}