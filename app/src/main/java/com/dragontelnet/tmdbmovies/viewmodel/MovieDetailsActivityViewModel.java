package com.dragontelnet.tmdbmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dragontelnet.tmdbmovies.datasource.MainActivityRepo;
import com.dragontelnet.tmdbmovies.datasource.MoviesDetailsActivityRepo;
import com.dragontelnet.tmdbmovies.di.MyDaggerInjection;
import com.dragontelnet.tmdbmovies.model.VideoResult;

import java.util.List;

import javax.inject.Inject;

public class MovieDetailsActivityViewModel extends AndroidViewModel {

    @Inject
    public MoviesDetailsActivityRepo moviesDetailsActivityRepo;

    public MovieDetailsActivityViewModel(@NonNull Application application) {
        super(application);
        MyDaggerInjection.getReposComponent().inject(this);
    }

    public LiveData<List<VideoResult>> getMovieTrailers(String movieId)
    {
        return moviesDetailsActivityRepo.getMovieTrailers(movieId);
    }
}
