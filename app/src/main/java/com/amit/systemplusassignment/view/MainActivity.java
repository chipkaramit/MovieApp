package com.amit.systemplusassignment.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

import com.amit.systemplusassignment.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CardView  cardPlaying , cardPopular , cardTopRated , cardUpcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // linearLatest = findViewById(R.id.ll_latest);
        cardPlaying = findViewById(R.id.cv_now_playing);

        cardPopular = findViewById(R.id.cv_popular);
        cardTopRated = findViewById(R.id.cv_top_rated);
        cardUpcoming = findViewById(R.id.cv_upcoming);
        //linearLatest.setOnClickListener(this);
        cardPlaying.setOnClickListener(this);
        cardPopular.setOnClickListener(this);
        cardTopRated.setOnClickListener(this);
        cardUpcoming.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch(v.getId()){


            case R.id.cv_now_playing:
                Intent intentPlaying = new Intent(this, MovieListActivity.class);
                intentPlaying.putExtra("now playing",1);
                this.startActivity(intentPlaying);
                break;

            case R.id.cv_popular:
                Intent intentPopular = new Intent(this, MovieListActivity.class);
                intentPopular.putExtra("popular",2);
                this.startActivity(intentPopular);
                break;

            case R.id.cv_top_rated:
                Intent intentTopRated = new Intent(this, MovieListActivity.class);
                intentTopRated.putExtra("top rated",3);
                this.startActivity(intentTopRated);
                break;

            case R.id.cv_upcoming:
                Intent intentUpcoming = new Intent(this, MovieListActivity.class);
                intentUpcoming.putExtra("upcoming",4);
                this.startActivity(intentUpcoming);
                break;
        }
    }
}
