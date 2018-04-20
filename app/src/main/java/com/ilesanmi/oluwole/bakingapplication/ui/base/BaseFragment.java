package com.ilesanmi.oluwole.bakingapplication.ui.base;


import android.support.v4.app.Fragment;

/**
 * Created by abayomi on 19/03/2018.
 */

public abstract class BaseFragment extends Fragment implements MvpView{

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
}
