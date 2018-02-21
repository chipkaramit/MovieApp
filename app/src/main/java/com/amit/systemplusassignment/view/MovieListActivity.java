package com.amit.systemplusassignment.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.amit.systemplusassignment.R;
import com.amit.systemplusassignment.model.MovieModel;
import com.amit.systemplusassignment.presenter.JSONResponse;
import com.amit.systemplusassignment.presenter.RequestInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by amitchipkar on 11/02/18.
 */

public class MovieListActivity  extends AppCompatActivity{

    private RecyclerView recyclerViewMovieList;
    private MovieListAdapter movieListAdapter;
    private Context context;
    ArrayList<MovieModel> movieModelList;
    MovieModel movieModel;
    private SearchView searchView;

    public static final String BASE_URL ="https://api.themoviedb.org/";
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("Movie App");

        context = this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        configureLayout();

        Intent intent = getIntent();
        if (intent.hasExtra("now playing")) {
            getSupportActionBar().setTitle("Now Playing");
            callApiNowPlaying();

        } else if (intent.hasExtra("popular")) {
            getSupportActionBar().setTitle("Popular");
            callApiPopular();
        } else if (intent.hasExtra("top rated")) {
            getSupportActionBar().setTitle("Top Rated");
            callApiTopRated();
        } else if (intent.hasExtra("upcoming")) {
            getSupportActionBar().setTitle("Upcoming");
            callApiUpcoming();
        }




    }


    private void configureLayout() {
        recyclerViewMovieList = findViewById(R.id.recycler_view_movie);
        recyclerViewMovieList.setHasFixedSize(true);
        recyclerViewMovieList.setLayoutManager(new GridLayoutManager(context, 2));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(context, R.dimen.item_offset);
        recyclerViewMovieList.addItemDecoration(itemDecoration);



        // white background notification bar
      //  whiteNotificationBar(recyclerViewMovieList);

    }


    private void callApiNowPlaying()

    {
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getNowPlayingJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                progressDialog.hide();

                JSONResponse jsonResponse = response.body();
                movieModelList = new ArrayList<>(Arrays.asList(jsonResponse.getMovieModels()));
                movieListAdapter = new MovieListAdapter(movieModelList);
                recyclerViewMovieList.setAdapter(movieListAdapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                progressDialog.hide();
                Log.d("Error",t.getMessage());
            }
        });

    }

    private void callApiPopular() {
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getPopularJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                progressDialog.hide();

                JSONResponse jsonResponse = response.body();
                movieModelList = new ArrayList<>(Arrays.asList(jsonResponse.getMovieModels()));
                movieListAdapter = new MovieListAdapter(movieModelList);
                recyclerViewMovieList.setAdapter(movieListAdapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                progressDialog.hide();
                Log.d("Error",t.getMessage());
            }
        });
    }

    private void callApiTopRated() {
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getTopRatedJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                progressDialog.hide();

                JSONResponse jsonResponse = response.body();
                movieModelList = new ArrayList<>(Arrays.asList(jsonResponse.getMovieModels()));
                movieListAdapter = new MovieListAdapter(movieModelList);
                recyclerViewMovieList.setAdapter(movieListAdapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                progressDialog.hide();
                Log.d("Error",t.getMessage());
            }
        });

    }

    private void callApiUpcoming() {
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getUpcomingJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                progressDialog.hide();

                JSONResponse jsonResponse = response.body();
                movieModelList = new ArrayList<>(Arrays.asList(jsonResponse.getMovieModels()));
                movieListAdapter = new MovieListAdapter(movieModelList);
                recyclerViewMovieList.setAdapter(movieListAdapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                progressDialog.hide();
                Log.d("Error",t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                movieListAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }*/

    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
   /* private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }*/

    }
