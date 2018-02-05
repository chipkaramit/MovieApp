package com.amit.systemplusassignment.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.amit.systemplusassignment.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout linearLatest , linearPlaying , linearPopular , linearTopRated , linearUpcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // linearLatest = findViewById(R.id.ll_latest);
        linearPlaying = findViewById(R.id.ll_now_playing);

        linearPopular = findViewById(R.id.ll_popular);
        linearTopRated = findViewById(R.id.ll_top_rated);
        linearUpcoming = findViewById(R.id.ll_upcoming);
        //linearLatest.setOnClickListener(this);
        linearPlaying.setOnClickListener(this);
        linearPopular.setOnClickListener(this);
        linearTopRated.setOnClickListener(this);
        linearUpcoming.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch(v.getId()){

//            case R.id.ll_latest:
//                Intent intent = new Intent(this, MoviesListActivity.class);
//                intent.putExtra("latest",1);
//                this.startActivity(intent);
//                break;

            case R.id.ll_now_playing:
                Intent intentPlaying = new Intent(this, MoviesListActivity.class);
                intentPlaying.putExtra("now playing",2);
                this.startActivity(intentPlaying);
                break;

            case R.id.ll_popular:
                Intent intentPopular = new Intent(this, MoviesListActivity.class);
                intentPopular.putExtra("popular",3);
                this.startActivity(intentPopular);
                break;

            case R.id.ll_top_rated:
                Intent intentTopRated = new Intent(this, MoviesListActivity.class);
                intentTopRated.putExtra("top rated",4);
                this.startActivity(intentTopRated);
                break;

            case R.id.ll_upcoming:
                Intent intentUpcoming = new Intent(this, MoviesListActivity.class);
                intentUpcoming.putExtra("upcoming",5);
                this.startActivity(intentUpcoming);
                break;
        }
    }
}
