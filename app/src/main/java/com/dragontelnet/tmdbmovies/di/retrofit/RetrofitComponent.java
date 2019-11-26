package com.dragontelnet.tmdbmovies.di.retrofit;

import com.dragontelnet.tmdbmovies.datasource.MainActivityRepo;
import com.dragontelnet.tmdbmovies.datasource.MoviesDetailsActivityRepo;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {

    void inject(MainActivityRepo mainActivityRepo);
    void inject(MoviesDetailsActivityRepo moviesDetailsActivityRepo);
}
