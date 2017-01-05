package au.com.vicfaith.android.retrofitdaggersample.modules;

import au.com.vicfaith.android.retrofitdaggersample.components.PerActivity;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;
import au.com.vicfaith.android.retrofitdaggersample.ui.HomePresenter;
import au.com.vicfaith.android.retrofitdaggersample.ui.HomeView;
import dagger.Module;
import dagger.Provides;

@Module
public class HomePresenterModule {
    ApiService apiService;
    HomeView homeView;

    public HomePresenterModule(ApiService apiService, HomeView homeView) {
        this.apiService = apiService;
        this.homeView = homeView;
    }

    @Provides
    @PerActivity
    HomePresenter providePresenter() {
        return new HomePresenter(apiService, homeView);
    }
}