package com.example.themoviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.themoviedb.DetailActivity;
import com.example.themoviedb.R;
import com.example.themoviedb.model.Results;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>  {
    private List<Results> movies;
    private Context context;


    public MoviesAdapter(List<Results> results, Context context) {
        this.movies = results;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MovieViewHolder holder, int position) {
        final String link ="https://image.tmdb.org/t/p/w500"+movies.get(position).getPosterPath();

        final String description,title;

        title = movies.get(position).getOriginalTitle();
        description = movies.get(position).getOverview();
        Glide.with(context).load(link).into(holder.movieImage);
        holder.movieTitle.setText(movies.get(position).getOriginalTitle());
        holder.movieLanguage.setText(movies.get(position).getOriginalLanguage());
        holder.movieRealseDate.setText(movies.get(position).getReleaseDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image Link", link);
                intent.putExtra("title", title);
                intent.putExtra("overview",description);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }



    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle,movieLanguage,movieRealseDate;
        ImageView movieImage;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieLanguage = itemView.findViewById(R.id.movieLangauge);
            movieRealseDate = itemView.findViewById(R.id.movieReleaseDate);
            movieImage = itemView.findViewById(R.id.imgMovie);
        }
    }
}
