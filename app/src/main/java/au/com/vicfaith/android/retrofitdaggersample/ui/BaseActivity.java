package au.com.vicfaith.android.retrofitdaggersample.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import au.com.vicfaith.android.retrofitdaggersample.MyApplication;
import au.com.vicfaith.android.retrofitdaggersample.components.DaggerMyActivityComponent;
import au.com.vicfaith.android.retrofitdaggersample.components.MyActivityComponent;
import au.com.vicfaith.android.retrofitdaggersample.modules.NetworkModule;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;


public class BaseActivity extends AppCompatActivity {

    protected ApiService apiService;
    protected SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyActivityComponent component = DaggerMyActivityComponent.builder()
                .myAppComponent(((MyApplication) getApplication()).getComponent())
                .networkModule(new NetworkModule(new File(getCacheDir(), "responses")))
                .build();
        component.inject(this);

        apiService = component.getApiService();
        sharedPreferences = ((MyApplication) getApplication()).getComponent().getSharedPreferences();
    }
}