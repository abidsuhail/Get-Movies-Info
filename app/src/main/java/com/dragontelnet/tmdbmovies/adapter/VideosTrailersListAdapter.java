package com.dragontelnet.tmdbmovies.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dragontelnet.tmdbmovies.BuildConfig;
import com.dragontelnet.tmdbmovies.R;
import com.dragontelnet.tmdbmovies.databinding.VideoThumbnailListLayoutBinding;
import com.dragontelnet.tmdbmovies.model.VideoResult;
import com.dragontelnet.tmdbmovies.ui.MainActivity;
import com.dragontelnet.tmdbmovies.ui.MovieDetailsActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

public class VideosTrailersListAdapter extends RecyclerView.Adapter<VideosTrailersListAdapter.ViewHolder>
{
    private List<VideoResult> videoResultList;
    private Context context;
    private VideoThumbnailListLayoutBinding videoThumbnailListLayoutBinding;
    public static final String YOUTUBE_API_KEY= BuildConfig.YOUTUBE_API_KEY;

    private static final String TAG = "VideosTrailersListAdapt";

    public VideosTrailersListAdapter(Context context) {
        this.context = context;
    }

    public void setVideoResultList(List<VideoResult> videoResultList) {
        this.videoResultList = videoResultList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        videoThumbnailListLayoutBinding = DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()),R.layout.video_thumbnail_list_layout,parent,false);
        return new ViewHolder(videoThumbnailListLayoutBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final VideoResult trailerVideo=videoResultList.get(position);

        videoThumbnailListLayoutBinding.setVideo(trailerVideo);

        holder.videoThumbnailListLayoutBinding
                .youtubeThumbnailView
                .initialize(YOUTUBE_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                //setting trailer video key
                youTubeThumbnailLoader.setVideo(trailerVideo.getKey());

                //getting trailer thumbnail
                youTubeThumbnailLoader
                        .setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) { }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) { }
        });


        holder.videoThumbnailListLayoutBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Activity activity=(Activity) context;
                Intent intent = YouTubeStandalonePlayer
                        .createVideoIntent(activity,YOUTUBE_API_KEY, trailerVideo.getKey(), 0, true, false);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return videoResultList!=null?videoResultList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        VideoThumbnailListLayoutBinding videoThumbnailListLayoutBinding;
        public ViewHolder(@NonNull VideoThumbnailListLayoutBinding videoThumbnailListLayoutBinding) {
            super(videoThumbnailListLayoutBinding.getRoot());
            this.videoThumbnailListLayoutBinding=videoThumbnailListLayoutBinding;
        }
    }
}
