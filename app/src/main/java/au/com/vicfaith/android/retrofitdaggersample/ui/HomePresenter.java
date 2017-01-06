package au.com.vicfaith.android.retrofitdaggersample.ui;

import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;
import au.com.vicfaith.android.retrofitdaggersample.network.RequestListener;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class HomePresenter {
    private final ApiService apiService;
    private final HomeView homeView;
    private CompositeSubscription subscriptions;

    public HomePresenter(ApiService apiService, HomeView view) {
        this.apiService = apiService;
        this.homeView = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCityList() {
        homeView.showProgressBar();

        Subscription subscription = apiService.getCityList(new RequestListener<CityListResponse>() {
            @Override
            public void onRequestError(Throwable e) {
                homeView.hideProgressBar();
                homeView.onFailure(e.getMessage());
            }

            @Override
            public void onRequestSuccess(CityListResponse response) {
                homeView.hideProgressBar();
                homeView.getCityListSuccess(response);
            }
        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}