package au.com.vicfaith.android.retrofitdaggersample.modules;

import android.app.Activity;

import java.lang.ref.WeakReference;

import au.com.vicfaith.android.retrofitdaggersample.components.PerActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class MyActivityModule {
    Activity activity;

    public MyActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    WeakReference<Activity> provideWeakActivity() {
        return new WeakReference<>(activity);
    }
}
