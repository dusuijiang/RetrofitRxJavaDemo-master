package com.example.dusj.retrofitrxjavademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.dusj.retrofitrxjavademo.gank.GankActivity;
import com.example.dusj.retrofitrxjavademo.movie.MovieActivity;

public class HomeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);

        findViewById(R.id.movie_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MovieActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.award_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, GankActivity.class);
                startActivity(intent);
            }
        });
    }
}
