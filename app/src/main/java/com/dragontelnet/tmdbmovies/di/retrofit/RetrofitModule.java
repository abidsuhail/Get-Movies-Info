package com.dragontelnet.tmdbmovies.di.retrofit;

import com.dragontelnet.tmdbmovies.service.GetMoviesServices;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    private static final String BASE_URL="https://api.themoviedb.org/3/movie/";

    @Singleton
    @Provides
    GetMoviesServices provideRetrofit()
    {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GetMoviesServices.class);
    }
}
