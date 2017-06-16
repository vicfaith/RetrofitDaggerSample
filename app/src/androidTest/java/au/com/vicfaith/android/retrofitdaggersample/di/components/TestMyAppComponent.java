package au.com.vicfaith.android.retrofitdaggersample.di.components;

import javax.inject.Singleton;

import au.com.vicfaith.android.retrofitdaggersample.ExampleInstrumentedTest;
import au.com.vicfaith.android.retrofitdaggersample.di.modules.TestNetworkModule;
import au.com.vicfaith.android.retrofitdaggersample.components.MyAppComponent;
import au.com.vicfaith.android.retrofitdaggersample.di.modules.TestDataModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.HomePresenterModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.MyAppModule;
import dagger.Component;

@Singleton
@Component(modules = {
        MyAppModule.class,
        TestDataModule.class,
        TestNetworkModule.class
})

public interface TestMyAppComponent extends MyAppComponent {

    TestMyActivityComponent plus(HomePresenterModule module);

    void inject(ExampleInstrumentedTest test);
}