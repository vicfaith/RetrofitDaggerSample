package au.com.vicfaith.android.retrofitdaggersample.ui;

import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;

public interface HomeView {
    void showProgressBar();

    void hideProgressBar();

    void showError(String error);

    void showCityList(CityListResponse response);
}