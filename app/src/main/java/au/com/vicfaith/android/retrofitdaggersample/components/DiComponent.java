package au.com.vicfaith.android.retrofitdaggersample.components;

import android.support.v4.app.Fragment;

import javax.inject.Singleton;

import au.com.vicfaith.android.retrofitdaggersample.modules.NetworkApiModule;
import au.com.vicfaith.android.retrofitdaggersample.ui.BaseActivity;
import dagger.Component;

@Singleton
@Component(modules = {NetworkApiModule.class})

public interface DiComponent {
    void inject(BaseActivity activity);

    void inject(Fragment fragment);
}