package com.androideity.recyclerviewexample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Condesa on 05/02/16.
 */
public class Anime {

    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public String title;
    @SerializedName("image")
    public String image;
    @SerializedName("score")
    public double score;
    @SerializedName("episodes")
    public double episodes;

    public Field status;
    public List<Field> genres;
}
