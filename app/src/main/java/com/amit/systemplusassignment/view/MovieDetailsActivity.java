package com.amit.systemplusassignment.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.amit.systemplusassignment.R;
import com.bumptech.glide.Glide;


public class MovieDetailsActivity extends AppCompatActivity {

    ImageView backDropPath;
    TextView movieOverview;
    String backDrop , overview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        backDrop = intent.getStringExtra("BACKDROP_PATH");
        overview = intent.getStringExtra("OVERVIEW");
       // movieModels =  intent.getSerializableExtra("movieList");

        initViews();
        getMovieDetailsData();

    }



    private void initViews()
    {
        backDropPath = findViewById(R.id.img_movie_details);
        movieOverview = findViewById(R.id.tv_movie_overview);
    }

    private void getMovieDetailsData()
    {

       /* for(int i=0; i<movieModels.size(); i++)
        {*/

            Glide.with(this)
                    .load(backDrop)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(backDropPath);

            movieOverview.setText(overview);
       // }
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
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
