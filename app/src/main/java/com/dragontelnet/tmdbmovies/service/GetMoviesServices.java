package com.dragontelnet.tmdbmovies.service;

import com.dragontelnet.tmdbmovies.model.Movie;
import com.dragontelnet.tmdbmovies.model.Result;
import com.dragontelnet.tmdbmovies.model.VideoTrailer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetMoviesServices {

    @GET("upcoming")
    Call<Movie> getUpComingMovies(@Query("api_key") String apiKey);

    @GET("popular")
    Call<Movie> getPopularMovies(@Query("api_key") String apiKey);

    @GET("now_playing")
    Call<Movie> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("{movie_id}/videos")
    Call<VideoTrailer> getMovieTrailers(@Path ("movie_id") String movieId,@Query("api_key") String apiKey);

    @GET("top_rated")
    Call<Movie> getTopRatedMovies(@Query("api_key") String apiKey);
}
