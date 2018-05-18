package com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
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
import com.google.android.exoplayer2.util.Util;
import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.di.components.ActivityComponent;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseFragment;


import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abayomi on 19/03/2018.
 */

public class StepDetailFragment extends BaseFragment implements StepDetailMvpView {

    public static final String FRAGMENT_ID = "Step_Detail_Id";


    @BindView(R.id.exo_player_view)
    SimpleExoPlayerView mExoPlayerView;

    @Inject
    StepDetailPresenter<StepDetailMvpView> mPresenter;

    private SimpleExoPlayer player;

    private String uriString = " ";

    boolean playWhenReady = true;
    int playbackPosition = 0;
    int currentWindow = 0;

    public static StepDetailFragment newInstance() {
        Bundle arguments = new Bundle();
        StepDetailFragment fragment = new StepDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step, container, false);

        ButterKnife.bind(this, view);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            ButterKnife.bind(this, view);
            mPresenter.onAttach(this);
        }

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Recipe recipe = bundle.getParcelable("RECIPE");
            int clickedPosition = bundle.getInt("POSITION_CLICKED");

            setUri(recipe.getSteps().get(clickedPosition).getVideoURL());
        }


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //        if (orientation == Configuration.ORIENTATION_LANDSCAPE && !isTwoPane) {
//            // Expand video, hide description, hide system UI
//            expandVideoView(exoPlayerView);
//            setViewVisibility(descriptionCard, false);
//            hideSystemUI();
//        }
        if (player == null) {
            initializePlayer();
            Log.i("ExoPlayerActivity", "InitializePlayer");
        }

    }

    @Override
    public void updateViewInActivity(ArrayList<Recipe> recipeList) {

    }

    private void initializePlayer() {

        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();

        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        player.seekTo(currentWindow, playbackPosition);

        mExoPlayerView.setPlayer(player);

        player.addListener(this);

        Uri uri = Uri.parse(getUri());
        DataSource.Factory mediaDataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "BakingApplication"), defaultBandwidthMeter);

        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        MediaSource mediaSource = new ExtractorMediaSource(uri,
                mediaDataSourceFactory, extractorsFactory, null, null);

        player.prepare(mediaSource, true, false);
        player.setPlayWhenReady(playWhenReady);
    }


    public void setUri(String url) {
        uriString = url;
    }

    private String getUri() {
        return uriString;
    }
    @Override
    public void onStart() {
        super.onStart();
        initializePlayer();

    }

    @Override
    public void onPause() {
        super.onPause();
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

    @Override
    public void onResume() {
        super.onResume();

    }

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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }


    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        Log.i("ExoPlayer", "onPlayerError");
    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    //if screen orientation is changed then restore the state of the exoplayer
    //stop multiple audio streams from being downloaded and played at the same time
    //stop multiple audio streams from being played when back button pressed

    //you dont have to write all if's the above if has been written within lifecycle

    // if relating to views and displays should be carefully considered to be part of the lifecycle
}

//View.SYSTEM_UI_FLAG_LAYOUT_STABLE


//
//        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//        | View.SYSTEM_UI_FLAG_FULLSCREEN
//        | View.SYSTEM_UI_FLAG_IMMERSIVE