package au.com.vicfaith.android.retrofitdaggersample.modules;

import android.content.Context;

import au.com.vicfaith.android.retrofitdaggersample.MyApplication;
import au.com.vicfaith.android.retrofitdaggersample.components.PerApp;
import dagger.Module;
import dagger.Provides;

@Module
public class MyAppModule {
    MyApplication app;

    public MyAppModule(MyApplication app) {
        this.app = app;
    }

    @Provides
    @PerApp
    MyApplication provideApplication() {
        return app;
    }

    @Provides
    @PerApp
    Context provideContext() {
        return app;
    }
}
