package au.com.vicfaith.android.retrofitdaggersample.di.components;

import au.com.vicfaith.android.retrofitdaggersample.ExampleInstrumentedTest;
import au.com.vicfaith.android.retrofitdaggersample.components.MyActivityComponent;
import au.com.vicfaith.android.retrofitdaggersample.components.PerActivity;
import au.com.vicfaith.android.retrofitdaggersample.modules.HomePresenterModule;
import dagger.Subcomponent;


@PerActivity
@Subcomponent(modules = {HomePresenterModule.class})

public interface TestMyActivityComponent extends MyActivityComponent {

    void inject(ExampleInstrumentedTest test);
}