package com.amit.systemplusassignment.model;

import java.io.Serializable;

/**
 * Created by amitchipkar on 04/02/18.
 */

public class MovieModel{


    private String title;
    private String poster_path;
    private String backdrop_path;
    private String overview;
    private String release_date;

    public String getRelease_date() {
        return release_date;
    }



    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return "http://image.tmdb.org/t/p/w500" + poster_path;
    }

    public String getBackdrop_path() {
        return "http://image.tmdb.org/t/p/w500" + backdrop_path;
    }

    public String getOverview() {
        return overview;
    }





//implements Serializable
   /* public MovieModel( String movieTitle , String movieYear , String posterUrl , String overview , String backDropUrl) {
        this.movieTitle = movieTitle;
        this.movieYear = movieYear;
        this.posterUrl = posterUrl;
        this.overview = overview;
        this.backDropUrl = backDropUrl;
    }*/



}