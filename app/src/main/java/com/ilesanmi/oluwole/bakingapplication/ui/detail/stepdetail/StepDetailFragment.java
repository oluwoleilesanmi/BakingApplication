package com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;
import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.di.components.ActivityComponent;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abayomi on 19/03/2018.
 */

public class StepDetailFragment extends BaseFragment implements StepDetailMvpView {

    public static final String FRAGMENT_ID = "item_id";

    @BindView(R.id.exo_player_view)
    SimpleExoPlayerView mExoPlayerView;

    @Inject
    StepDetailPresenter<StepDetailMvpView> mPresenter;

    private SimpleExoPlayer player;

    private boolean playWhenReady = true;
    private String uriString = " ";
    private long playbackPosition;
    private int currentWindow;



    public static StepDetailFragment newInstance(Parcelable recipe, int position) {
        Bundle arguments = new Bundle();
        arguments.putParcelable("RECIPE", recipe);
        arguments.putInt("POSITION_CLICKED", position);
        StepDetailFragment fragment = new StepDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);

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
    public void updateViewInActivity(ArrayList<Recipe> recipeList) {

    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(),new DefaultLoadControl());
        player.seekTo(currentWindow, playbackPosition);

        mExoPlayerView.setPlayer(player);
        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        Uri uri = Uri.parse(getUri());
        DataSource.Factory mediaDataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "detailFragment"));

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
    public void onResume() {
        super.onResume();
        hideSystemUi();
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
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        mExoPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
