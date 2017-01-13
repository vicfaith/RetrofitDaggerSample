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

    private static MyApplication application;

    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        injectDependencies();
    }

    protected void injectDependencies() {
        myAppComponent = DaggerMyAppComponent.builder()
                .myAppModule(new MyAppModule(this))
                .dataModule(new DataModule(this))
                .networkModule(new NetworkModule(new File(getCacheDir(), "responses")))
                .build();
    }

    public MyAppComponent getComponent() {
        return myAppComponent;
    }
}