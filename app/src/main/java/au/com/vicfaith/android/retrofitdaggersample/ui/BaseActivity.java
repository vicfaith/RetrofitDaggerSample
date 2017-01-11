package au.com.vicfaith.android.retrofitdaggersample.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;


public class BaseActivity extends AppCompatActivity {

    @Inject
    protected ApiService apiService;

    @Inject
    protected SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}