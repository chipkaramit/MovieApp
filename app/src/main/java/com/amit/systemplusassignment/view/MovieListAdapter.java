package com.amit.systemplusassignment.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amit.systemplusassignment.R;
import com.amit.systemplusassignment.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by amitchipkar on 04/02/18.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> implements Filterable {

    private Context mContext;
    private List<MovieModel> movieModelList = new ArrayList<>();

    private List<MovieModel> filteredMovieList = new ArrayList<>();

    public MovieListAdapter(Context context) {
        mContext = context;


    }

    public void setMovieModelList(List<MovieModel> movieModels) {
        movieModelList.clear();
        movieModelList.addAll(movieModels);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MovieModel movieModel = movieModelList.get(position);
        holder.movieTitle.setText(movieModel.getMovieTitle());
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        String inputDateStr = movieModel.getMovieYear();
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);
        holder.movieYear.setText(outputDateStr);
        Picasso.with(mContext).load(movieModel.getPosterUrl()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, movieModelList.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {

                    filteredMovieList = movieModelList;
                } else {

                    ArrayList<MovieModel> filteredList = new ArrayList<>();

                    for (MovieModel movieModel : movieModelList) {

                        if (movieModel.getMovieTitle().toLowerCase().contains(charString)) {

                            filteredList.add(movieModel);
                        }
                    }

                    filteredMovieList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredMovieList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredMovieList = (ArrayList<MovieModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView movieTitle, movieYear;
        public final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            movieTitle = view.findViewById(R.id.tv_movie_title);
            movieYear = view.findViewById(R.id.tv_movie_year);
            imageView = view.findViewById(R.id.img_movie);
        }
    }
}
