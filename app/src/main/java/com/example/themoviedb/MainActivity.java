package com.example.themoviedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.themoviedb.RestApi.ApiInterface;
import com.example.themoviedb.RestApi.RetroApi;
import com.example.themoviedb.adapter.MoviesAdapter;
import com.example.themoviedb.model.MovieResponse;
import com.example.themoviedb.model.Results;

import java.util.List;

import io.shubh.superiortoastlibrary.SuperiorToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final static String API_KEY = "4e44d9029b1270a757cddc766a1bcb63";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movieRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





        if (API_KEY.isEmpty()) {
            SuperiorToast.makeSuperiorToast(MainActivity.this, "Enter Api Key")
                    .show();
            return;
        }

        ApiInterface apiService =
                RetroApi.getClient().create(ApiInterface.class);


        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Results> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, getApplicationContext()));

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                SuperiorToast.makeSuperiorToast(MainActivity.this, "Request failed")
                        .show();
            }
        });


    }
}