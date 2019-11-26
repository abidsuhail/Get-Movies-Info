package com.dragontelnet.tmdbmovies.ui;

import android.os.Bundle;
import com.dragontelnet.tmdbmovies.R;
import com.dragontelnet.tmdbmovies.adapter.VideosTrailersListAdapter;
import com.dragontelnet.tmdbmovies.databinding.ActivityMovieDetailsBinding;
import com.dragontelnet.tmdbmovies.di.MyDaggerInjection;
import com.dragontelnet.tmdbmovies.model.Result;
import com.dragontelnet.tmdbmovies.model.VideoResult;
import com.dragontelnet.tmdbmovies.viewmodel.MovieDetailsActivityViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.RatingBar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.dragontelnet.tmdbmovies.ui.MainActivity.MOVIE_OBJ;

@Singleton
public class MovieDetailsActivity extends AppCompatActivity  {

    private Result movie;

    private ActivityMovieDetailsBinding activityMovieDetailsBinding;

    private Observer<List<VideoResult>> observer;

    private RecyclerView videoListRV;

    private RatingBar ratingBar;

    private VideosTrailersListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        activityMovieDetailsBinding= DataBindingUtil
                .setContentView(this,R.layout.activity_movie_details);

        ratingBar=activityMovieDetailsBinding.contentMovie.ratingBar;

        videoListRV=activityMovieDetailsBinding.contentMovie.videosListRv;
        adapter=new VideosTrailersListAdapter(this);
        videoListRV.setAdapter(adapter);
        videoListRV.setLayoutManager(new LinearLayoutManager(this));

        setObserver(); //important
        Toolbar toolbar = activityMovieDetailsBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent().hasExtra(MOVIE_OBJ)) {
            initDetails();
        }

    }


    private MovieDetailsActivityViewModel getViewModel() {
        return ViewModelProviders
                .of(this).get(MovieDetailsActivityViewModel.class);
    }


    private void setObserver()
    {
        observer=new Observer<List<VideoResult>>() {
            @Override
            public void onChanged(List<VideoResult> videoResults) {
                    adapter.setVideoResultList(videoResults);
            }
        };
    }
    private void initDetails() {
        movie = (Result) getIntent().getSerializableExtra(MOVIE_OBJ);
        setTitle(movie.getTitle());
        activityMovieDetailsBinding.setMovie(movie);
        getMovieTrailersList(Integer.toString(movie.getId()));
    }

    private void getMovieTrailersList(String movieId)
    {
        getViewModel().getMovieTrailers(movieId).observe(this,observer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
