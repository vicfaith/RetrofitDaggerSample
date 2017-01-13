package au.com.vicfaith.android.retrofitdaggersample.components;

import au.com.vicfaith.android.retrofitdaggersample.HomePresenterDaggerTest;
import au.com.vicfaith.android.retrofitdaggersample.modules.HomePresenterModule;
import dagger.Subcomponent;


@PerActivity
@Subcomponent(modules = {HomePresenterModule.class})

public interface TestMyActivityComponent extends MyActivityComponent {

    void inject(HomePresenterDaggerTest test);
}