package au.com.vicfaith.android.retrofitdaggersample.ui;

import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;
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

        Subscription subscription = apiService.getCityList(new ApiService.GetCityListCallback() {

            @Override
            public void onSuccess(CityListResponse response) {
                homeView.hideProgressBar();
                homeView.getCityListSuccess(response);
            }

            @Override
            public void onError(Throwable error) {
                homeView.hideProgressBar();
                homeView.onFailure(error.getMessage());
            }
        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}