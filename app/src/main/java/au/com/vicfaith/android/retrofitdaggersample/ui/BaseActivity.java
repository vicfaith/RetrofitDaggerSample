package au.com.vicfaith.android.retrofitdaggersample.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;


public class BaseActivity extends AppCompatActivity {

    protected ApiService apiService;
    protected SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}