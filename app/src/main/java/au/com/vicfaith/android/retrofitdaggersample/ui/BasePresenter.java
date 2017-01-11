package au.com.vicfaith.android.retrofitdaggersample.ui;

import android.support.annotation.NonNull;

public abstract class BasePresenter<V> {

    protected V mView;

    public void attachView(@NonNull V view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }

    protected final boolean isViewAttached() {
        return mView != null;
    }
}