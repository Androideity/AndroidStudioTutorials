package com.androideity.recyclerviewexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.androideity.recyclerviewexample.adapter.AnimeAdapter;
import com.androideity.recyclerviewexample.model.Anime;
import com.androideity.recyclerviewexample.model.AnimeCollection;
import com.androideity.recyclerviewexample.utils.Constants;
import com.androideity.recyclerviewexample.utils.SpacesItemDecoration;
import com.androideity.recyclerviewexample.utils.Utility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AnimeAdapter.OnAnimeSelected {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private List<Anime> animes;
    private AnimeAdapter animeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpData();
    }

    private void setUpData(){
        new Thread() {
            public void run() {
                JSONObject jsonObject = Utility.loadJSONObjectFromAsset(getApplicationContext(),
                        Constants.JSON_FILE_NAME);
                Gson gson = new Gson();
                AnimeCollection animeCollection = gson.fromJson(jsonObject.toString(), AnimeCollection.class);
                animes = animeCollection.animes;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setUpRecyclerView();
                    }
                });
            }
        }.start();
    }

    private void setUpRecyclerView(){
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        //SpacesItemDecoration decoration = new SpacesItemDecoration(20);
        //recyclerView.addItemDecoration(decoration);
        animeAdapter = new AnimeAdapter(this, animes);
        recyclerView.setAdapter(animeAdapter);
    }

    @Override
    public void onAnimeClick(int animeId, String animeTitle) {

    }
}
