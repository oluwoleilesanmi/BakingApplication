package com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;

import android.view.ViewGroup;

import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.exoplayer2.util.Util;
import com.ilesanmi.oluwole.bakingapplication.R;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.ilesanmi.oluwole.bakingapplication.di.components.ActivityComponent;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseFragment;
import com.ilesanmi.oluwole.bakingapplication.utils.ExoUtil;

/**
 * Created by abayomi on 19/03/2018.
 */

public class StepDetailFragment extends BaseFragment implements StepDetailMvpView {

    @BindView(R.id.exo_player_view)
    SimpleExoPlayerView mExoPlayerView;

    @BindView(R.id.recipe_step_detail_image)
    ImageView stepDetailThumbnail;

    @Inject
    StepDetailMvpPresenter<StepDetailMvpView> mPresenter;


    private SimpleExoPlayer player;
    private String videoUrl = " ";
    private boolean playWhenReady = true;
    private long playbackPosition = 0;
    private int currentWindow = 0;

    public static StepDetailFragment newInstance(String description, String videoUrl,
                                                 String imageUrl) {
        Bundle arguments = new Bundle();
        arguments.putString("description", description);
        arguments.putString("video", videoUrl);
        arguments.putString("image", imageUrl);
        StepDetailFragment fragment = new StepDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stepdetail, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            ButterKnife.bind(this, view);
            mPresenter.onAttach(this);
        }
        if (savedInstanceState != null) {
            mPresenter.retrieveVideoDataInSharedPref();
        }

        videoUrl = getArguments().getString("video");
        String imageUrl = getArguments().getString("image");

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(this)
                    .load(imageUrl)
                    .into(stepDetailThumbnail);
            setImageViewVisibility(stepDetailThumbnail, true);
        } else {
            setImageViewVisibility(stepDetailThumbnail, false);
        }


        return view;
    }

    private void setImageViewVisibility(View view, boolean imageAvailable) {
        if (imageAvailable) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    //The onSaveInstanceState of a fragment is not gaurenteed to be called after onPause.
    //Encountered some unexpected behaviour when trying to implement saved state on rotation with viewpager.
    //More experiments can be done to confirm claims.
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("WHEN_SCREEN_ROTATES", 1);
    }

    private void initializePlayer() {
        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();

        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        player.seekTo(currentWindow, playbackPosition);

        mExoPlayerView.setPlayer(player);

        Uri uri = Uri.parse(videoUrl);
        DataSource.Factory mediaDataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "BakingApplication"), defaultBandwidthMeter);

        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        MediaSource mediaSource = new ExtractorMediaSource(uri,
                mediaDataSourceFactory, extractorsFactory, null, null);

        player.prepare(mediaSource, true, false);
        player.setPlayWhenReady(playWhenReady);
    }


    @Override
    public void onResume() {
        super.onResume();
        initializePlayer();
    }


    @Override
    public void onPause() {
        super.onPause();
        this.playWhenReady = player.getPlayWhenReady();
        this.playbackPosition = Math.max(0, player.getCurrentPosition());
        this.currentWindow = player.getCurrentWindowIndex();
        storeVideoDataInPref();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void releasePlayer() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;

        }
    }

    private void storeVideoDataInPref() {
        mPresenter.storeVideoDataInSharedPref(videoUrl, player.getPlayWhenReady(),
                Math.max(0, player.getCurrentPosition()), player.getCurrentWindowIndex());
    }


    //used to make ui dissapear
    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        mExoPlayerView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }


    @Override
    public void updateVideoView(String videoUrl, Boolean playWhenReady, Long playBackPosition, int currentWindowIndex) {
        this.videoUrl = videoUrl;
        this.playWhenReady = playWhenReady;
        this.playbackPosition = playBackPosition;
        this.currentWindow = currentWindowIndex;
    }


}