package au.com.vicfaith.android.retrofitdaggersample.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestDataModule {
    static final String PREFS_DEFAULT = "preferences";

    Context context;

    public TestDataModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return context.getApplicationContext().getSharedPreferences(PREFS_DEFAULT, Context.MODE_PRIVATE);
    }
}