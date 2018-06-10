package com.ilesanmi.oluwole.bakingapplication.ui.detail.activity.stepactivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.step.StepFragment;

public class StepActivity extends BaseActivity implements StepMvpView {

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, StepActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        StepFragment stepFragment =
                (StepFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.step_fragment_container);

        if (stepFragment == null) {
            stepFragment = StepFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.step_fragment_container,stepFragment);
            transaction.commit();
      }

    }




}
