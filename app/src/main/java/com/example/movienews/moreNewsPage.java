package com.example.movienews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class moreNewsPage extends AppCompatActivity {
    TextView titlee,release_data,description,vote_average;
    ImageView imageBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_news_page);
        titlee = findViewById(R.id.title);
        release_data = findViewById(R.id.release_data);
        description = findViewById(R.id.description);
        imageBackground = findViewById(R.id.imageBackground);
        vote_average = findViewById(R.id.vote_average);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String overview = intent.getStringExtra("overview");
        String release_date = intent.getStringExtra("release_date");
        String backdrop_path = intent.getStringExtra("backdrop_path");
        String votee_average = intent.getStringExtra("vote_average");


        titlee.setText(title);
        release_data.setText(release_date);
        description.setText(overview);
        vote_average.setText(votee_average);




        Picasso.with(moreNewsPage.this).load("https://image.tmdb.org/t/p/w500"+backdrop_path).into(imageBackground);




    }
}