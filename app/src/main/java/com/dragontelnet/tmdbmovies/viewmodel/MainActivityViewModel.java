package com.dragontelnet.tmdbmovies.viewmodel;


import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.dragontelnet.tmdbmovies.datasource.MainActivityRepo;
import com.dragontelnet.tmdbmovies.di.MyDaggerInjection;
import com.dragontelnet.tmdbmovies.model.Result;

import java.util.List;

import javax.inject.Inject;

public class MainActivityViewModel extends AndroidViewModel {

    private Application application;


    @Inject
    public MainActivityRepo mainActivityRepo;

    private static final String TAG = "MainActivityViewModel";

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        MyDaggerInjection.getReposComponent().inject(this);
    }

    public LiveData<List<Result>> getUpcomingMovies() {
        return mainActivityRepo.getUpComingMovies();
    }
    public LiveData<List<Result>> getPopularMovies() {
        return mainActivityRepo.getPopularMoviesList();
    }
    public LiveData<List<Result>> getNowPlayingMovies() {

        return mainActivityRepo.getNowPlaying();
    }
    public LiveData<List<Result>> getTopRatedMovies() {
        return mainActivityRepo.getTopRatedMovies();
    }

}
