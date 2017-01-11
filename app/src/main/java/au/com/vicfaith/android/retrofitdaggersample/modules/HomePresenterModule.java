package au.com.vicfaith.android.retrofitdaggersample.modules;

import android.content.SharedPreferences;

import au.com.vicfaith.android.retrofitdaggersample.components.PerActivity;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;
import au.com.vicfaith.android.retrofitdaggersample.ui.HomePresenter;
import au.com.vicfaith.android.retrofitdaggersample.ui.HomeView;
import dagger.Module;
import dagger.Provides;

@Module
public class HomePresenterModule {
    HomeView homeView;

    public HomePresenterModule(HomeView homeView) {
        this.homeView = homeView;
    }

    @Provides
    @PerActivity
    public HomeView provideView() {
        return homeView;
    }

    @Provides
    @PerActivity
    HomePresenter providePresenter(ApiService apiService, SharedPreferences sharedPreferences) {
        HomePresenter presenter = new HomePresenter(apiService, sharedPreferences);
        presenter.attachView(homeView);
        return presenter;
    }
}