package au.com.vicfaith.android.retrofitdaggersample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import au.com.vicfaith.android.retrofitdaggersample.MyApplication;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiInterface;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;
import retrofit2.Retrofit;


public class BaseActivity extends AppCompatActivity {
    @Inject
    Retrofit retrofit;

    protected ApiService apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MyApplication) getApplication()).getComponent().inject(this);

        apiService = new ApiService(retrofit.create(ApiInterface.class));
    }
}