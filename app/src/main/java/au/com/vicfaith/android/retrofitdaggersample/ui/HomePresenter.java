package au.com.vicfaith.android.retrofitdaggersample.ui;

import android.content.SharedPreferences;

import javax.inject.Inject;

import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;
import au.com.vicfaith.android.retrofitdaggersample.network.RequestListener;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class HomePresenter extends BasePresenter<HomeView> {
    ApiService apiService;
    SharedPreferences sharedPreferences;

    private CompositeSubscription subscriptions;

    @Inject
    public HomePresenter(ApiService apiService, SharedPreferences sharedPreferences) {
        this.apiService = apiService;
        this.sharedPreferences = sharedPreferences;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCityList() {
        if (!isViewAttached()) {
            return;
        }

        mView.showProgressBar();

        Subscription subscription = apiService.getCityList(new RequestListener<CityListResponse>() {
            @Override
            public void onRequestError(Throwable e) {
                if (isViewAttached()) {
                    mView.hideProgressBar();
                    mView.onFailure(e.getMessage());
                }
            }

            @Override
            public void onRequestSuccess(CityListResponse response) {
                if (isViewAttached()) {
                    mView.hideProgressBar();
                    mView.getCityListSuccess(response);
                }
            }
        });

        subscriptions.add(subscription);
    }

    @Override
    public void detachView() {
        super.detachView();

        if (subscriptions != null && !subscriptions.isUnsubscribed()) {
            subscriptions.unsubscribe();
        }
    }
}