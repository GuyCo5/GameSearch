package com.afeka.gamesearch.Controller;

import android.content.Context;
import android.util.Log;

import com.afeka.gamesearch.Layout.VideoGameBoundary;
import com.afeka.gamesearch.Model.User;
import com.afeka.gamesearch.Model.VideoGame;
import com.afeka.gamesearch.R;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GameRestIntegration {

    private String baseUrl;
    private OnRestInteractionListener binder;
    public enum DELETE_TYPE {ALL, SINGLE}

    public GameRestIntegration(OnRestInteractionListener binder, Context context) {
        this.binder = binder;
        baseUrl = context.getString(R.string.server_url);
    }

    public void performSearch(String text,FILTER_BY filter){
        ArrayList<VideoGame> videoGameList = new ArrayList<>();
        String filterParams= "/games";

        if (filter!=FILTER_BY.ALL){
            filterParams += "/" + filter.toString().toLowerCase() + "/" + text;
        }
        Log.e("RestGetGame url:", baseUrl + filterParams);
        try {
            videoGameList = new RestTaskGetGames(baseUrl + filterParams).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        binder.onRestGetComplete(videoGameList);
    }

    public void addGame(VideoGame game, User user){
        String urlExtra = "/game/" + user.getUserName();
        VideoGameBoundary vgb = new VideoGameBoundary(game);
        try {
            vgb = new RestTaskPostGame(baseUrl + urlExtra,vgb).execute().get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        VideoGame videoGameReturn = vgb.toEntity();
        binder.onRestAddComplete(videoGameReturn);
    }

    public void updateGame (VideoGame game, User user){
        String urlExtra = "/game/" + user.getUserName();
        VideoGameBoundary videoGameBoundary = new VideoGameBoundary(game);
        new RestTaskPutGame(baseUrl+urlExtra,videoGameBoundary).execute();
        binder.onRestUpdateComplete(game);
    }


    public void deleteGame (DELETE_TYPE deleteType, VideoGame videoGame, User user){
        String urlExtra = "/delete/";
        if (deleteType == DELETE_TYPE.ALL){
             urlExtra += user.getUserName();
        } else if (deleteType == DELETE_TYPE.SINGLE){
            urlExtra += user.getUserName() + "/" + videoGame.getGameName();
        }
        new RestTaskDeleteGame(baseUrl+ urlExtra);
        binder.onRestDeleteComplete();
    }

    public interface OnRestInteractionListener {
        void onRestGetComplete(ArrayList<VideoGame> videoGameList);
        void onRestAddComplete(VideoGame videoGame);
        void onRestDeleteComplete();
        void onRestUpdateComplete(VideoGame videoGame);
    }
}
