package au.com.vicfaith.android.retrofitdaggersample;

import android.app.Application;

import java.io.File;

import au.com.vicfaith.android.retrofitdaggersample.components.DaggerMyAppComponent;
import au.com.vicfaith.android.retrofitdaggersample.components.MyAppComponent;
import au.com.vicfaith.android.retrofitdaggersample.modules.DataModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.MyAppModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.NetworkModule;

public class MyApplication extends Application {
    private MyAppComponent myAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        myAppComponent = DaggerMyAppComponent.builder()
                .myAppModule(new MyAppModule(this))
                .dataModule(new DataModule(this))
                .networkModule(new NetworkModule(new File(getCacheDir(), "responses")))
                .build();

        myAppComponent.inject(this);
    }

    public MyAppComponent getComponent() {
        return myAppComponent;
    }
}