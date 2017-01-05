package au.com.vicfaith.android.retrofitdaggersample.modules;

import android.content.Context;
import android.content.SharedPreferences;

import au.com.vicfaith.android.retrofitdaggersample.components.PerApp;
import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    static final String PREFS_DEFAULT = "preferences";

    Context context;

    public DataModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerApp
    SharedPreferences provideSharedPreferences() {
        return context.getApplicationContext().getSharedPreferences(PREFS_DEFAULT, Context.MODE_PRIVATE);
    }
}
