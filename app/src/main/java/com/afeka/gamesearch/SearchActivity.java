package com.afeka.gamesearch;


import android.os.Bundle;

import com.afeka.gamesearch.Controller.FILTER_BY;
import com.afeka.gamesearch.Controller.GameRestIntegration;
import com.afeka.gamesearch.Model.USERS;
import com.afeka.gamesearch.Model.VideoGame;
import com.afeka.gamesearch.View.GameAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class SearchActivity extends AppCompatActivity
        implements SearchFragment.OnFragmentInteractionListener,
                    GameRestIntegration.OnRestInteractionListener,GameAdapter.OnItemClickListener {
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

        mRecyclerView = findViewById(R.id.gameRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new GameAdapter(videoGameList);
        mAdapter.setOnItemClickListener(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        fragmentManager = getSupportFragmentManager();
        searchFragment = new SearchFragment();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userManager = new UserManager(getApplicationContext());

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().add(R.id.flContent, searchFragment).addToBackStack(null).commit();
                fab.hide();
            }
        });

        gameRestIntegration = new GameRestIntegration(this, this);
        gameRestIntegration.performSearch("", FILTER_BY.ALL);
    }


    @Override
    public void onFragmentInteraction(String text, FILTER_BY filter) {
        fragmentManager.popBackStack();
        gameRestIntegration.performSearch(text, filter);
        fab.show();
    }


    @Override
    public void onRestGetComplete(ArrayList<VideoGame> videoGameList) {
        this.videoGameList.clear();
        this.videoGameList = videoGameList;
        mAdapter.setVideoGameList(this.videoGameList);
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
        if (type == USERS.ADMIN) {
            getMenuInflater().inflate(R.menu.toolbar_admin, menu);
        } else if (type == USERS.PLAYER) {
            getMenuInflater().inflate(R.menu.toolbar_user, menu);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Log.e("toolbar item:", id + "");
        if (id == R.id.action_disconnect2 || id == R.id.action_disconnect) {
            userManager.clearUser();
            Toast.makeText(getBaseContext(), "Logout!", Toast.LENGTH_SHORT).show();
            finish();
        } else if (id == R.id.action_add_game) {

        } else if (id == R.id.action_delete_all) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {
        USERS type = userManager.getUserType();
        if (type == USERS.ADMIN) {
            fragmentManager.beginTransaction().add(R.id.flContent, searchFragment).addToBackStack(null).commit();
        } else if (type == USERS.PLAYER) {

        }
    }
}