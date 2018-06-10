package com.ilesanmi.oluwole.bakingapplication;

import android.support.test.runner.AndroidJUnitRunner;

import com.squareup.rx2.idler.Rx2Idler;

import io.reactivex.plugins.RxJavaPlugins;

//Code inspired by work from https://github.com/tir38/RxIdler.git.
class RxJavaTestRunner extends AndroidJUnitRunner {

    @Override
    public void onStart() {
        RxJavaPlugins.setInitIoSchedulerHandler(
                Rx2Idler.create("RxJava 2.x IO Scheduler"));

        RxJavaPlugins.setInitComputationSchedulerHandler(
                Rx2Idler.create("RxJava 2.x Computation Scheduler"));

        RxJavaPlugins.setInitNewThreadSchedulerHandler(
                Rx2Idler.create("RxJava 2.x New Thread Scheduler"));

        super.onStart();
    }
}
