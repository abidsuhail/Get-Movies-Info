package com.dragontelnet.tmdbmovies.di;

import android.app.Application;

import com.dragontelnet.tmdbmovies.di.adapter.DaggerMovieListAdapterComponent;
import com.dragontelnet.tmdbmovies.di.adapter.MovieListAdapterComponent;
import com.dragontelnet.tmdbmovies.di.adapter.MoviesListAdapterModule;
import com.dragontelnet.tmdbmovies.di.repositories.DaggerRepositoryComponent;
import com.dragontelnet.tmdbmovies.di.repositories.RepositoryComponent;
import com.dragontelnet.tmdbmovies.di.repositories.RepositoryModule;
import com.dragontelnet.tmdbmovies.di.retrofit.DaggerRetrofitComponent;
import com.dragontelnet.tmdbmovies.di.retrofit.RetrofitComponent;

public class MyDaggerInjection extends Application {


    private static MovieListAdapterComponent movieListAdapterComponent;
    private static RetrofitComponent retrofitComponent;
    private static RepositoryComponent repositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpMovieListAdapterComponent();
        setUpRetrofitComponent();
        setUpRepositoryComponent();
    }


    private void setUpRepositoryComponent() {
        repositoryComponent=DaggerRepositoryComponent
                .builder()
                .repositoryModule(new RepositoryModule(getApplicationContext()))
                .build();
    }

    private void setUpRetrofitComponent() {
        retrofitComponent=DaggerRetrofitComponent.create();
    }

    private void setUpMovieListAdapterComponent() {
        movieListAdapterComponent= DaggerMovieListAdapterComponent
                .builder()
                .moviesListAdapterModule(new MoviesListAdapterModule(getApplicationContext()))
                .build();

    }

    public static MovieListAdapterComponent getMoviesListAdpterComponent()
    {
        return movieListAdapterComponent;
    }


    public static RetrofitComponent getRetrofitComponent()
    {
        return retrofitComponent;
    }

    public static RepositoryComponent getReposComponent()
    {
        return repositoryComponent;
    }

}
