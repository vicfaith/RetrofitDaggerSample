package au.com.vicfaith.android.retrofitdaggersample.components;

import javax.inject.Singleton;

import au.com.vicfaith.android.retrofitdaggersample.modules.DataModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.HomePresenterModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.MyAppModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.NetworkModule;
import au.com.vicfaith.android.retrofitdaggersample.ui.MainActivity;
import dagger.Component;

@Singleton
@Component(modules = {
        MyAppModule.class,
        DataModule.class,
        NetworkModule.class
})

public interface MyAppComponent {

    void inject(MainActivity activity);

    MyActivityComponent plus(HomePresenterModule module);
}