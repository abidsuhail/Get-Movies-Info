package com.dragontelnet.tmdbmovies.di.adapter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.databinding.DataBindingUtil;

import com.dragontelnet.tmdbmovies.R;
import com.dragontelnet.tmdbmovies.adapter.MoviesListAdapter;
import com.dragontelnet.tmdbmovies.databinding.ActivityMainBinding;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesListAdapterModule {

    private Context context;

    public MoviesListAdapterModule(Context context) {
        this.context = context;
    }


    @Singleton
    @Provides
    MoviesListAdapter providesMoviesListAdapter()
    {
       return new MoviesListAdapter(context);
    }
}
