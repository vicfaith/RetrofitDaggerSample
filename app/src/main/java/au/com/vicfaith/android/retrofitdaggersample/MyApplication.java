package au.com.vicfaith.android.retrofitdaggersample;

import android.app.Application;

import au.com.vicfaith.android.retrofitdaggersample.components.DaggerDiComponent;
import au.com.vicfaith.android.retrofitdaggersample.components.DiComponent;

public class MyApplication extends Application {
    DiComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerDiComponent.builder().build();
    }

    public DiComponent getComponent() {
        return component;
    }
}