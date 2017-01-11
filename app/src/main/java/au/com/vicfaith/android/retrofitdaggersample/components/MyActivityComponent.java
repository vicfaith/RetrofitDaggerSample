package au.com.vicfaith.android.retrofitdaggersample.components;

import au.com.vicfaith.android.retrofitdaggersample.modules.HomePresenterModule;
import au.com.vicfaith.android.retrofitdaggersample.ui.MainFragment;
import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {HomePresenterModule.class})

public interface MyActivityComponent {

    void inject(MainFragment fragment);
}