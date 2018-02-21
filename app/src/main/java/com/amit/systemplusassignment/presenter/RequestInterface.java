package com.amit.systemplusassignment.presenter;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by amitchipkar on 11/02/18.
 */

public interface RequestInterface {

    @GET("3/movie/now_playing?api_key=f5542fa3768ac7f29fd0629ce7b4463d&language=en-US&page=1")
    Call<JSONResponse> getNowPlayingJSON();

    @GET("3/movie/popular?api_key=f5542fa3768ac7f29fd0629ce7b4463d&language=en-US&page=1")
    Call<JSONResponse> getPopularJSON();


    @GET("3/movie/top_rated?api_key=f5542fa3768ac7f29fd0629ce7b4463d&language=en-US&page=1")
    Call<JSONResponse> getTopRatedJSON();


    @GET("3/movie/upcoming?api_key=f5542fa3768ac7f29fd0629ce7b4463d&language=en-US&page=1")
    Call<JSONResponse> getUpcomingJSON();
}