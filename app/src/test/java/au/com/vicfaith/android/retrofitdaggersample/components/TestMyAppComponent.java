package au.com.vicfaith.android.retrofitdaggersample.components;

import javax.inject.Singleton;

import au.com.vicfaith.android.retrofitdaggersample.modules.DataModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.HomePresenterModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.MyAppModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.NetworkModule;
import dagger.Component;

@Singleton
@Component(modules = {
        MyAppModule.class,
        DataModule.class,
        NetworkModule.class
})

public interface TestMyAppComponent extends MyAppComponent {

    TestMyActivityComponent plus(HomePresenterModule module);
}