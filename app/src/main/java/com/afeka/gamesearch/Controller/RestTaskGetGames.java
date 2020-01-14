package com.afeka.gamesearch.Controller;

import android.os.AsyncTask;
import com.afeka.gamesearch.Layout.VideoGameBoundary;
import com.afeka.gamesearch.Model.VideoGame;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;

public class RestTaskGetGames extends AsyncTask<Void,Void, ArrayList<VideoGame>> {

    private String url;
    private RestTemplate restTemplate;

    public RestTaskGetGames(String url) {
        this.url = url;
        restTemplate = new RestTemplate();
    }

    @Override
    protected ArrayList<VideoGame> doInBackground(Void... voids) {
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
    }

    @Override
    protected void onPostExecute(ArrayList<VideoGame> videoGameResponseEntity) {
        super.onPostExecute(videoGameResponseEntity);
    }
}
