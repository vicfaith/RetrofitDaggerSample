package au.com.vicfaith.android.retrofitdaggersample.components;

import javax.inject.Singleton;

import au.com.vicfaith.android.daggersample.MainActivity;
import au.com.vicfaith.android.daggersample.modules.NetworkApiModule;
import dagger.Component;

@Singleton
@Component(modules = {NetworkApiModule.class})

public interface DiComponent {
    // to update the fields in your activities
    void inject(MainActivity activity);
}