package com.dragontelnet.tmdbmovies.di.repositories;


import com.dragontelnet.tmdbmovies.datasource.MainActivityRepo;
import com.dragontelnet.tmdbmovies.datasource.MoviesDetailsActivityRepo;
import com.dragontelnet.tmdbmovies.viewmodel.MainActivityViewModel;
import com.dragontelnet.tmdbmovies.viewmodel.MovieDetailsActivityViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RepositoryModule.class)
public interface RepositoryComponent {

    void inject(MainActivityViewModel mainActivityViewModel);
    void inject(MovieDetailsActivityViewModel movieDetailsActivityViewModel);
}
