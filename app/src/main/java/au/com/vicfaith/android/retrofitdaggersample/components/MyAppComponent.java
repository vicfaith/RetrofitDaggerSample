package au.com.vicfaith.android.retrofitdaggersample.components;

import android.content.SharedPreferences;

import au.com.vicfaith.android.retrofitdaggersample.MyApplication;
import au.com.vicfaith.android.retrofitdaggersample.modules.DataModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.MyAppModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.NetworkModule;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;
import dagger.Component;

@PerApp
@Component(modules = {MyAppModule.class, DataModule.class, NetworkModule.class})

public interface MyAppComponent {
    MyApplication getApplication();

    SharedPreferences getSharedPreferences();

    ApiService getApiService();

    void inject(MyApplication app);
}