package com.androideity.recyclerviewexample.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.androideity.recyclerviewexample.R;
import com.androideity.recyclerviewexample.adapter.AnimeAdapter;
import com.androideity.recyclerviewexample.model.Anime;
import com.androideity.recyclerviewexample.model.AnimeCollection;
import com.androideity.recyclerviewexample.utils.Constants;
import com.androideity.recyclerviewexample.utils.SpacesItemDecoration;
import com.androideity.recyclerviewexample.utils.Utility;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;

public class MainActivity extends AppCompatActivity implements AnimeAdapter.OnAnimeSelected {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.coordinator_layout) CoordinatorLayout coordinatorLayout;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private List<Anime> animes;
    private AnimeAdapter animeAdapter;
    private boolean isListView = true;

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
        recyclerView.setHasFixedSize(true);

        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);

        animeAdapter = new AnimeAdapter(this, animes);

        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(animeAdapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleAdapter.setFirstOnly(false);
        scaleAdapter.setInterpolator(new OvershootInterpolator(2f));

        recyclerView.setAdapter(scaleAdapter);
    }

    @Override
    public void onAnimeClick(Anime anime, ImageView imageView) {
        Snackbar.make(coordinatorLayout, anime.title, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_list_visualization) {
            changeListVisualization(item);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeListVisualization(MenuItem menuItem) {
        if (isListView) {
            staggeredGridLayoutManager.setSpanCount(2);
            menuItem.setIcon(R.drawable.ic_action_list);
            menuItem.setTitle(R.string.menu_show_as_list);
            isListView = false;
        } else {
            staggeredGridLayoutManager.setSpanCount(1);
            menuItem.setIcon(R.drawable.ic_action_grid);
            menuItem.setTitle(R.string.menu_show_as_grid);
            isListView = true;
        }
    }
}
