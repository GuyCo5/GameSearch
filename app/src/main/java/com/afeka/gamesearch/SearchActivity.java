package com.afeka.gamesearch;


import android.os.Bundle;

import com.afeka.gamesearch.Controller.FILTER_BY;
import com.afeka.gamesearch.Controller.GameRestIntegration;
import com.afeka.gamesearch.Model.USERS;
import com.afeka.gamesearch.Model.VideoGame;
import com.afeka.gamesearch.View.GameAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class SearchActivity extends AppCompatActivity
        implements SearchFragment.OnFragmentInteractionListener,
                    GameRestIntegration.OnRestInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private GameAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private FragmentManager fragmentManager;
    private SearchFragment searchFragment;
    private ArrayList<VideoGame> videoGameList;

    private FloatingActionButton fab;
    private GameRestIntegration gameRestIntegration;

    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        videoGameList = new ArrayList<>();
//        videoGameList.add(new VideoGame("Game1", "Fps",R.drawable.ic_android));
//        videoGameList.add(new VideoGame("Game2", "Kids",R.drawable.ic_child_care));
//        videoGameList.add(new VideoGame("Game3", "Racing",R.drawable.ic_local_taxi));


        mRecyclerView = findViewById(R.id.gameRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new GameAdapter(videoGameList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        fragmentManager = getSupportFragmentManager();
        searchFragment = new SearchFragment();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userManager = new UserManager(getBaseContext());

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().add(R.id.flContent,searchFragment).addToBackStack(null).commit();
                fab.hide();
            }
        });

        gameRestIntegration = new GameRestIntegration(this,this);
        gameRestIntegration.performSearch("",FILTER_BY.ALL);
    }


    @Override
    public void onFragmentInteraction(String text, FILTER_BY filter) {
//        fragmentManager.beginTransaction().remove(searchFragment).commit();
        fragmentManager.popBackStack();
        gameRestIntegration.performSearch(text,filter);
        fab.show();
    }


    @Override
    public void onRestGetComplete(ArrayList<VideoGame> videoGameList) {
        this.videoGameList = videoGameList;
        mAdapter.setVideoGameList(videoGameList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRestAddComplete(VideoGame videoGame) {

    }

    @Override
    public void onRestDeleteComplete() {

    }

    @Override
    public void onRestUpdateComplete(VideoGame videoGame) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        USERS type = userManager.getUserType();
        if (type == USERS.ADMIN){
            getMenuInflater().inflate(R.menu.toolbar_admin, menu);
        } else if (type == USERS.PLAYER) {
            getMenuInflater().inflate(R.menu.toolbar_user, menu);
        }


        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}