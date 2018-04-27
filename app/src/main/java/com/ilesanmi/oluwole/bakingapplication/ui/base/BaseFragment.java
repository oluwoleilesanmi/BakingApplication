package com.ilesanmi.oluwole.bakingapplication.ui.base;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.ilesanmi.oluwole.bakingapplication.di.components.ActivityComponent;

/**
 * Created by abayomi on 19/03/2018.
 */

public abstract class BaseFragment extends Fragment implements MvpView{

    private BaseActivity mActivity;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
