package au.com.vicfaith.android.retrofitdaggersample.components;

import android.content.SharedPreferences;

import au.com.vicfaith.android.retrofitdaggersample.MyApplication;
import au.com.vicfaith.android.retrofitdaggersample.modules.DataModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.MyAppModule;
import dagger.Component;

@PerApp
@Component(modules = {MyAppModule.class, DataModule.class})

public interface MyAppComponent {
    MyApplication getApplication();

    SharedPreferences getSharedPreferences();

    void inject(MyApplication app);
}