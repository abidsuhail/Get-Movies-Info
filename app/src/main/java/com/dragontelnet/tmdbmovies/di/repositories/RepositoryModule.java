package com.dragontelnet.tmdbmovies.di.repositories;


import android.app.Application;
import android.content.Context;

import com.dragontelnet.tmdbmovies.datasource.MainActivityRepo;
import com.dragontelnet.tmdbmovies.datasource.MoviesDetailsActivityRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    Context context;

    public RepositoryModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    MainActivityRepo providesMainActivityRepo()
    {
        return new MainActivityRepo();
    }

    @Singleton
    @Provides
    MoviesDetailsActivityRepo providesMoviesDetailsActivityRepo()
    {
        return new MoviesDetailsActivityRepo(context);
    }
}
