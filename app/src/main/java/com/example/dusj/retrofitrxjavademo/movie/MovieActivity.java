package com.example.dusj.retrofitrxjavademo.movie;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.dusj.retrofitrxjavademo.R;
import com.example.dusj.retrofitrxjavademo.http.Fault;

import java.util.List;

import rx.functions.Action1;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener{
    private MovieLoader mMovieLoader;
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMovieLoader = new MovieLoader();
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle(R.string.movie_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new MovieDecoration());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mMovieAdapter = new MovieAdapter();
        mRecyclerView.setAdapter(mMovieAdapter);
        mSwipeRefreshLayout.setRefreshing(true);
        getMovieList(start);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                start = start+5;
                getMovieList(start);
            }
        });
    }

    /**
     * 获取电影列表
     */
    int start = 0;
    private void getMovieList(int start){
        mMovieLoader.getMovie(start,5).subscribe(new Action1<List<Movie>>() {
            @Override
            public void call(List<Movie> movies) {
                mMovieAdapter.setMovies(movies);
                mMovieAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("TAG","error message:"+throwable.getMessage());
                if(throwable instanceof Fault){
                    Fault fault = (Fault) throwable;
                    if(fault.getErrorCode() == 404){
                        //错误处理
                    }else if(fault.getErrorCode() == 500){
                        //错误处理
                    }else if(fault.getErrorCode() == 501){
                        //错误处理
                    }
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        //getMovieRx();
    }

    public static class MovieDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0,0,0,20);
        }
    }
}
