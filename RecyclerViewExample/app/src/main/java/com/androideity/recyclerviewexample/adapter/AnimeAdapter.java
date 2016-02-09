package com.androideity.recyclerviewexample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androideity.recyclerviewexample.R;
import com.androideity.recyclerviewexample.model.Anime;
import com.androideity.recyclerviewexample.model.Field;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Condesa on 07/02/16.
 */
public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder> {

    private AppCompatActivity appCompatActivity;
    private List<Anime> animes;
    private OnAnimeSelected onAnimeSelected;

    public AnimeAdapter(AppCompatActivity appCompatActivity, List<Anime> animes){
        this.appCompatActivity = appCompatActivity;
        this.animes = animes;
        try{
            onAnimeSelected = (OnAnimeSelected) appCompatActivity;
        }catch (ClassCastException e){
            throw new ClassCastException(appCompatActivity.toString()
                    + " must implement OnAnimeSelected");
        }
    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Anime anime = animes.get(position);
        Picasso.with(appCompatActivity.getApplicationContext())
                .load(anime.image)
                .into(holder.imageAnime);

        holder.labelTitle.setText(anime.title);
        StringBuilder stringBuilder = new StringBuilder();
        for(Field field : anime.genres){
            stringBuilder.append(String.format("%s | ", field.name));
        }
        holder.labelGenders.setText(stringBuilder.toString());
        holder.containerHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnimeSelected.onAnimeClick(anime.id, anime.title);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_anime, parent, false);
        return new ViewHolder(itemView);
    }

    public interface OnAnimeSelected {
        void onAnimeClick(int animeId, String animeTitle);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.container_holder) LinearLayout containerHolder;
        @Bind(R.id.label_title) TextView labelTitle;
        @Bind(R.id.label_genders) TextView labelGenders;
        @Bind(R.id.image_anime) ImageView imageAnime;

        private Anime anime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
