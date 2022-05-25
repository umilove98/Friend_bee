package com.example.friendsbee;

import android.os.Bundle;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewWriteRatingBar extends AppCompatActivity {

    private RatingBar ratingbar_small;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingbar_small = findViewById(R.id.ratingBar);
        ratingbar_small.setOnRatingBarChangeListener(new Listener());
    }

    class Listener implements RatingBar.OnRatingBarChangeListener
    {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            ratingbar_small.setRating(rating);
        }
    }
}
