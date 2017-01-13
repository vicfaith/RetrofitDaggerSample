package au.com.vicfaith.android.retrofitdaggersample.modules;

import android.content.Context;
import android.content.SharedPreferences;

import static org.mockito.Mockito.mock;

public class TestDataModule extends DataModule {
    public TestDataModule(Context context) {
        super(context);
    }

    @Override
    SharedPreferences provideSharedPreferences() {
        return mock(SharedPreferences.class);
    }
}