package com.dragontelnet.tmdbmovies.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dragontelnet.tmdbmovies.databinding.MovieLayoutBinding;
import com.dragontelnet.tmdbmovies.ui.MovieDetailsActivity;
import com.dragontelnet.tmdbmovies.R;
import com.dragontelnet.tmdbmovies.model.Result;

import java.util.List;

import static com.dragontelnet.tmdbmovies.ui.MainActivity.MOVIE_OBJ;


public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ViewHolder> {

    private Context context;
    private MovieLayoutBinding movieLayoutBinding;
    private List<Result> movieList;
    private static final String TAG = "MoviesListAdapter";
    public MoviesListAdapter(Context context) {
        this.context = context;
        Log.d(TAG, "MoviesListAdapter: "+this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        movieLayoutBinding= DataBindingUtil
                .inflate(LayoutInflater
                .from(parent.getContext()),R.layout.movie_layout,parent,false);
        return new ViewHolder(movieLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (movieList.get(position)!=null && context!=null) {
            Result movie =movieList.get(position);
            holder.movieLayoutBinding.setMovie(movie);
        }

    }

    @Override
    public int getItemCount() {
        return movieList!=null?movieList.size():0; //returning size of list,0 if null
    }

    public void setMoviesList(List<Result> list)
    {
        movieList=list;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {
        MovieLayoutBinding movieLayoutBinding;
        public ViewHolder(@NonNull final MovieLayoutBinding movieLayoutBinding) {
            super(movieLayoutBinding.getRoot());

            this.movieLayoutBinding=movieLayoutBinding;
            movieLayoutBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition()!=RecyclerView.NO_POSITION)
                    {
                        Result result = movieList.get(getAdapterPosition());
                        startMovieDetailsActivity(result);
                    }
                }
            });
        }

        private void startMovieDetailsActivity(Result result)
        {
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            intent.putExtra(MOVIE_OBJ, result);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
