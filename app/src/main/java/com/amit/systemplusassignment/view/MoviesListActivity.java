package com.amit.systemplusassignment.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.amit.systemplusassignment.R;
import com.amit.systemplusassignment.model.MovieModel;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class MoviesListActivity extends AppCompatActivity {

    private  RecyclerView recyclerViewMovieList;
    private MovieListAdapter movieListAdapter;
    private Disposable disposable;
    private Context context;
    private List<MovieModel> movieModelList;
    MovieModel movieModel;
    Observable<List<MovieModel>> listObservable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_movies_list);

        configureLayout();


    }

    private void configureLayout()
    {
        setContentView(R.layout.activity_movies_list);
        context = this;
        recyclerViewMovieList = findViewById(R.id.recycler_view_movie);
        recyclerViewMovieList.setHasFixedSize(true);
        recyclerViewMovieList.setLayoutManager(new GridLayoutManager(context, 2));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(context, R.dimen.item_offset);
        recyclerViewMovieList.addItemDecoration(itemDecoration);
        movieListAdapter = new MovieListAdapter(this);
        recyclerViewMovieList.setAdapter(movieListAdapter);
        movieModelList = new ArrayList<>();


        Intent intent = getIntent();
        if (intent.hasExtra("now playing"))
        {
            createObservableNowPlaying();
        }
        else if (intent.hasExtra("popular"))
        {
            createObservablePopular();
        }
        else if (intent.hasExtra("top rated"))
        {
            createObservableTopRated();
        }
        else if (intent.hasExtra("upcoming"))
        {
            createObservableUpcoming();
        }
    }

    private void createObservableNowPlaying() {

        listObservable = Observable.just(getNowPlayingMovieList());
    }
    private void createObservablePopular() {

        listObservable = Observable.just(getPopularMovieList());
    }
    private void createObservableTopRated() {

        listObservable = Observable.just(getTopRatedMovieList());

    }
    private void createObservableUpcoming() {

        listObservable = Observable.just(getUpcomingMovieList());

    }




    public  List<MovieModel> getNowPlayingMovieList()
    {
       final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=f5542fa3768ac7f29fd0629ce7b4463d&language=en-US&page=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray newsArray = obj.getJSONArray("results");
                            for (int i = 0; i < newsArray.length(); i++) {
                                JSONObject newsObject = newsArray.getJSONObject(i);
                                movieModel = new MovieModel(newsObject.getString("title") , newsObject.getString("release_date")
                                , "http://image.tmdb.org/t/p/w500" + newsObject.getString("poster_path"));
                                movieModelList.add(movieModel);
                                movieListAdapter.notifyDataSetChanged();
                                VolleyLog.d("Data", response);
                               disposable = listObservable.subscribe(movieModelListß -> movieListAdapter.setMovieModelList(movieModelList));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setShouldCache(false);
        requestQueue.add(stringRequest);
        return movieModelList;

    }

    public  List<MovieModel> getPopularMovieList()
    {
        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=f5542fa3768ac7f29fd0629ce7b4463d&language=en-US&page=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray newsArray = obj.getJSONArray("results");
                            for (int i = 0; i < newsArray.length(); i++) {
                                JSONObject newsObject = newsArray.getJSONObject(i);
                                movieModel = new MovieModel(newsObject.getString("title") , newsObject.getString("release_date")
                                        , "http://image.tmdb.org/t/p/w500" + newsObject.getString("poster_path"));
                                movieModelList.add(movieModel);
                                movieListAdapter.notifyDataSetChanged();
                                VolleyLog.d("Data", response);
                                disposable = listObservable.subscribe(movieModelListß -> movieListAdapter.setMovieModelList(movieModelList));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setShouldCache(false);
        requestQueue.add(stringRequest);
        return movieModelList;

    }
    public  List<MovieModel> getTopRatedMovieList()
    {
        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=f5542fa3768ac7f29fd0629ce7b4463d&language=en-US&page=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray newsArray = obj.getJSONArray("results");
                            for (int i = 0; i < newsArray.length(); i++) {
                                JSONObject newsObject = newsArray.getJSONObject(i);
                                movieModel = new MovieModel(newsObject.getString("title") , newsObject.getString("release_date")
                                        , "http://image.tmdb.org/t/p/w500" + newsObject.getString("poster_path"));
                                movieModelList.add(movieModel);
                                movieListAdapter.notifyDataSetChanged();
                                VolleyLog.d("Data", response);
                                disposable = listObservable.subscribe(movieModelListß -> movieListAdapter.setMovieModelList(movieModelList));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setShouldCache(false);
        requestQueue.add(stringRequest);
        return movieModelList;

    }
    public  List<MovieModel> getUpcomingMovieList()
    {
        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=f5542fa3768ac7f29fd0629ce7b4463d&language=en-US&page=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray newsArray = obj.getJSONArray("results");
                            for (int i = 0; i < newsArray.length(); i++) {
                                JSONObject newsObject = newsArray.getJSONObject(i);
                                movieModel = new MovieModel(newsObject.getString("title") , newsObject.getString("release_date")
                                        , "http://image.tmdb.org/t/p/w500" + newsObject.getString("poster_path"));
                                movieModelList.add(movieModel);
                                movieListAdapter.notifyDataSetChanged();
                                VolleyLog.d("Data", response);
                                disposable = listObservable.subscribe(movieModelListß -> movieListAdapter.setMovieModelList(movieModelList));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setShouldCache(false);
        requestQueue.add(stringRequest);
        return movieModelList;

    }
    @Override
    protected void onStop() {
        super.onStop();
        if (disposable!=null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
