package au.com.vicfaith.android.retrofitdaggersample.modules;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import au.com.vicfaith.android.retrofitdaggersample.MyApplication;
import dagger.Module;
import dagger.Provides;

@Module
public class MyAppModule {
    MyApplication app;

    public MyAppModule(MyApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    MyApplication provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return app.getResources();
    }

}
