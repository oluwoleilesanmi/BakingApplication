package com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail;

import com.google.android.exoplayer2.ExoPlayer;
import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpView;

public interface StepDetailMvpView extends MvpView {
void updateVideoView(String videoUrl, Boolean playWhenReady, Long playBackPosition, int currentWindowIndex);

}
