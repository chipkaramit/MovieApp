package com.amit.systemplusassignment.model;

import java.io.Serializable;

/**
 * Created by amitchipkar on 04/02/18.
 */

public class MovieModel implements Serializable {

    private String movieYear;
    private String movieTitle;
    private String posterUrl;
    public MovieModel( String movieTitle , String movieYear , String posterUrl) {
        this.movieTitle = movieTitle;
        this.movieYear = movieYear;
        this.posterUrl = posterUrl;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }






    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }






    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }


    public String getMovieYear() {
        return movieYear;
    }



}