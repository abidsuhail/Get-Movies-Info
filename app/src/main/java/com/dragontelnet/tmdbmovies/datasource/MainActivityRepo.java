package com.dragontelnet.tmdbmovies.datasource;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dragontelnet.tmdbmovies.BuildConfig;
import com.dragontelnet.tmdbmovies.di.MyDaggerInjection;
import com.dragontelnet.tmdbmovies.model.Movie;
import com.dragontelnet.tmdbmovies.model.Result;
import com.dragontelnet.tmdbmovies.service.GetMoviesServices;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@Singleton
public class MainActivityRepo {

    @Inject
    public GetMoviesServices getMoviesServices;

    private static final String TAG = "MainActivityRepo";
    private List<Result> result;

    private MutableLiveData<List<Result>> upcomingMoviesMutable=new MutableLiveData<>();
    private MutableLiveData<List<Result>> getNowPlayingMutable=new MutableLiveData<>();
    private MutableLiveData<List<Result>> popularMoviesMutable=new MutableLiveData<>();
    private MutableLiveData<List<Result>> topRatedMoviesMutable=new MutableLiveData<>();


    public MainActivityRepo() {
        MyDaggerInjection.getRetrofitComponent().inject(this);
    }

    public MutableLiveData<List<Result>> getUpComingMovies()
    {
        Call<Movie> moviesList=getMoviesServices.getUpComingMovies(BuildConfig.TMDB_API_KEY);
        moviesList.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response)
            {
                if(response.isSuccessful() && response.body()!=null) {
                    result = response.body().getResults();
                    upcomingMoviesMutable.setValue(result);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
        return upcomingMoviesMutable;
    }

    public MutableLiveData<List<Result>> getNowPlaying()
    {
        Call<Movie> moviesList=getMoviesServices.getNowPlayingMovies(BuildConfig.TMDB_API_KEY);
        moviesList.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response)
            {
                if(response.isSuccessful() && response.body()!=null) {
                    result = response.body().getResults();
                    getNowPlayingMutable.setValue(result);
                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
        return getNowPlayingMutable;
    }
    public MutableLiveData<List<Result>> getPopularMoviesList()
    {
        Call<Movie> moviesList=getMoviesServices.getPopularMovies(BuildConfig.TMDB_API_KEY);
        moviesList.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response)
            {
                if(response.isSuccessful() && response.body()!=null) {
                    result = response.body().getResults();
                    popularMoviesMutable.setValue(result);

                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
        return popularMoviesMutable;
    }
    public MutableLiveData<List<Result>> getTopRatedMovies()
    {
        Call<Movie> moviesList=getMoviesServices.getTopRatedMovies(BuildConfig.TMDB_API_KEY);
        moviesList.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response)
            {
                if(response.isSuccessful() && response.body()!=null) {
                    result = response.body().getResults();
                    topRatedMoviesMutable.setValue(result);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
        return topRatedMoviesMutable;
    }

}
