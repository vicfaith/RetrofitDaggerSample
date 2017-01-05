package au.com.vicfaith.android.retrofitdaggersample.components;

import android.support.v4.app.Fragment;

import au.com.vicfaith.android.retrofitdaggersample.modules.NetworkModule;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;
import au.com.vicfaith.android.retrofitdaggersample.ui.BaseActivity;
import au.com.vicfaith.android.retrofitdaggersample.ui.HomePresenter;
import dagger.Component;

@PerActivity
@Component(dependencies = MyAppComponent.class, modules = {NetworkModule.class})

public interface MyActivityComponent {

    ApiService getApiService();

    void inject(BaseActivity activity);

    void inject(Fragment fragment);

    void inject(HomePresenter presenter);
}