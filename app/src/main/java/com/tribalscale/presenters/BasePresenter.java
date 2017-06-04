package com.tribalscale.presenters;

/**
 * Created by patel on 6/4/2017.
 */

public abstract class BasePresenter<T> implements IPresenter {

    protected T mView;

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    public void attachView(T view) {
        mView = view;
    }

    public T getView() {
        return mView;
    }
}
