package com.afeka.gamesearch.Controller;

import android.app.Activity;

import com.afeka.gamesearch.Model.GENRES;
import com.afeka.gamesearch.Model.VideoGame;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GameRestIntegration {

    private final int port = 8095;
    private ArrayList<VideoGame> videoGameList;
    private String baseUrl;

    private OnRestInteractionListener binder;
    private Activity activity;


    public GameRestIntegration(OnRestInteractionListener binder,Activity activity) {
        this.activity = activity;
        this.binder = binder;

        baseUrl = "http://10.0.2.2:" + port + "/games";
        videoGameList = new ArrayList<>();
    }

    public void performSearch(String text,FILTER_BY filter){
        videoGameList.clear();
        String filterParams;
        if (filter==FILTER_BY.ALL){
            filterParams = "";
        }
        else {
            filterParams = "/" + filter.toString() + "/" + text;
        }
        try {
            videoGameList = new RestTaskGetGames(baseUrl + filterParams, activity).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        initGameList();
        binder.onRestInteraction(videoGameList);
    }


    public interface OnRestInteractionListener {
        void onRestInteraction(ArrayList<VideoGame> videoGameList);
    }


    private void initGameList(){

        for (int i=1 ; i<9 ; i++){
           videoGameList.add(new VideoGame("call of duty " + i, GENRES.BattleRoyale,2000+i,"Activition"));
        }
    }


//    private boolean checkPermissions (){
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return false;
//        }
//        return true;
//        return false;
//    }
}
