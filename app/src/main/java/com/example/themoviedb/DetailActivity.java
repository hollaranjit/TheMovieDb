package com.example.themoviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
       TextView movietitle,movieDescription;
       ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movietitle = findViewById(R.id.detailTitle);
        movieDescription = findViewById(R.id.detailDes);
        imageView = findViewById(R.id.imgView);

        String link = getIntent().getStringExtra("Image Link");

        Glide.with(this).load(link).into(imageView);

        String title = getIntent().getStringExtra("title");
        String des = getIntent().getStringExtra("overview");


        movietitle.setText(title);
        movieDescription.setText(des);

    }
}