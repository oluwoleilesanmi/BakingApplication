package com.ilesanmi.oluwole.bakingapplication.ui.base;


import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.ilesanmi.oluwole.bakingapplication.di.components.ActivityComponent;
import com.ilesanmi.oluwole.bakingapplication.utils.CommonUtils;

import butterknife.Unbinder;

/**
 * Created by abayomi on 19/03/2018.
 */

public abstract class BaseFragment extends Fragment implements MvpView{

    private BaseActivity mActivity;
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;

    @Override
    public void showLoading() {
        hideLoading();
       mProgressDialog = CommonUtils.showLoadingDialog(this.getContext());
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
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

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }
    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
