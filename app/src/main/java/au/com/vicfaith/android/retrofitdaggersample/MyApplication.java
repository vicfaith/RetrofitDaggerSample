package au.com.vicfaith.android.retrofitdaggersample;

import android.app.Application;

import java.io.File;

import au.com.vicfaith.android.retrofitdaggersample.components.DaggerDiComponent;
import au.com.vicfaith.android.retrofitdaggersample.components.DiComponent;
import au.com.vicfaith.android.retrofitdaggersample.modules.NetworkApiModule;

public class MyApplication extends Application {
    private DiComponent diComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        File cacheFile = new File(getCacheDir(), "responses");
        diComponent = DaggerDiComponent.builder().networkApiModule(new NetworkApiModule(cacheFile)).build();
    }

    public DiComponent getComponent() {
        return diComponent;
    }
}