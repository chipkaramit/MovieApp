package com.amit.systemplusassignment.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.amit.systemplusassignment.R;
import com.amit.systemplusassignment.model.MovieModel;
import com.bumptech.glide.Glide;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by amitchipkar on 04/02/18.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> implements Filterable {


    private ArrayList<MovieModel> movieModelList;
    private ArrayList<MovieModel> movieModelFilteredList;
    private Context mContext;




    public MovieListAdapter(ArrayList<MovieModel> movieModelList ) {
        this.movieModelList = movieModelList;
        this.movieModelFilteredList = movieModelList;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

      //  Picasso.with(mContext).load(movieModelFilteredList.get(position).getPoster_path()).into(holder.imageView);

        Glide.with(mContext)
                .load(movieModelFilteredList.get(position).getPoster_path())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView);


        holder.movieTitle.setText(movieModelFilteredList.get(position).getTitle());
        String year = parseDateToddMMyyyy(movieModelFilteredList.get(position).getRelease_date());

        holder.movieYear.setText(year);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , MovieDetailsActivity.class);
                intent.putExtra("BACKDROP_PATH",movieModelFilteredList.get(position).getBackdrop_path());
                intent.putExtra("OVERVIEW",movieModelFilteredList.get(position).getOverview());
                mContext.startActivity(intent);
                //Toast.makeText(mContext, movieModelList.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieModelFilteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    movieModelFilteredList = movieModelList;
                } else {

                    ArrayList<MovieModel> filteredList = new ArrayList<>();

                    for (MovieModel movieModel : movieModelList) {

                        if (movieModel.getTitle().toLowerCase().contains(charString) || movieModel.getRelease_date().toLowerCase().contains(charString)) {

                            filteredList.add(movieModel);
                        }
                    }

                    movieModelFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = movieModelFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieModelFilteredList = (ArrayList<MovieModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView movieTitle, movieYear;
        public final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            movieTitle = view.findViewById(R.id.tv_movie_title);
            movieYear = view.findViewById(R.id.tv_movie_year);
            imageView = view.findViewById(R.id.img_movie);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                }
            });
        }
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-dd-MM";
        String outputPattern = "yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}
