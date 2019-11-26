package com.dragontelnet.tmdbmovies.di.adapter;

import com.dragontelnet.tmdbmovies.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MoviesListAdapterModule.class})
public interface MovieListAdapterComponent {

    void inject(MainActivity mainActivity);
}
