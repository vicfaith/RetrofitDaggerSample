package au.com.vicfaith.android.retrofitdaggersample;

import android.app.Application;

import au.com.vicfaith.android.retrofitdaggersample.components.DaggerMyAppComponent;
import au.com.vicfaith.android.retrofitdaggersample.components.MyAppComponent;
import au.com.vicfaith.android.retrofitdaggersample.modules.DataModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.MyAppModule;

public class MyApplication extends Application {
    private MyAppComponent myAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        myAppComponent = DaggerMyAppComponent.builder()
                .myAppModule(new MyAppModule(this))
                .dataModule(new DataModule(this))
                .build();

        myAppComponent.inject(this);
    }

    public MyAppComponent getComponent() {
        return myAppComponent;
    }
}