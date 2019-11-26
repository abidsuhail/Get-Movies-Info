package com.dragontelnet.tmdbmovies.datasource;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.dragontelnet.tmdbmovies.di.MyDaggerInjection;
import com.dragontelnet.tmdbmovies.di.retrofit.RetrofitComponent;
import com.dragontelnet.tmdbmovies.model.VideoResult;
import com.dragontelnet.tmdbmovies.model.VideoTrailer;
import com.dragontelnet.tmdbmovies.service.GetMoviesServices;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dragontelnet.tmdbmovies.BuildConfig.TMDB_API_KEY;

@Singleton
public class MoviesDetailsActivityRepo {


    private MutableLiveData<List<VideoResult>> videoResult=new MutableLiveData<>();

    @Inject
    public GetMoviesServices getMoviesServices;

    private Context context;

    private static final String TAG = "MoviesDetailsActivityRe";
    public MoviesDetailsActivityRepo(Context context) {
        this.context = context;
        MyDaggerInjection.getRetrofitComponent().inject(this);
    }

    public MutableLiveData<List<VideoResult>> getMovieTrailers(String movieId)
    {
        Call<VideoTrailer> trailerCall=getMoviesServices.getMovieTrailers(movieId,TMDB_API_KEY);
        trailerCall.enqueue(new Callback<VideoTrailer>() {
            @Override
            public void onResponse(Call<VideoTrailer> call, Response<VideoTrailer> response) {

                if(response.isSuccessful() && response.body()!=null && response.code()!=404) {
                    List<VideoResult> videoResults = response.body().getVideoResults();
                    videoResult.setValue(videoResults);
                }
            }

            @Override
            public void onFailure(Call<VideoTrailer> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        return videoResult;
    }
}
