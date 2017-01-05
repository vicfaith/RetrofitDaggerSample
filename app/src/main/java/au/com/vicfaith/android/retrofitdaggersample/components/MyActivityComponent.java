package au.com.vicfaith.android.retrofitdaggersample.components;

import android.support.v4.app.Fragment;

import au.com.vicfaith.android.retrofitdaggersample.modules.HomePresenterModule;
import au.com.vicfaith.android.retrofitdaggersample.ui.HomePresenter;
import au.com.vicfaith.android.retrofitdaggersample.ui.MainActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = MyAppComponent.class, modules = {HomePresenterModule.class})

public interface MyActivityComponent extends MyAppComponent {

    HomePresenter getHomePresenter();

    void inject(MainActivity activity);

    void inject(Fragment fragment);

    void inject(HomePresenter presenter);
}