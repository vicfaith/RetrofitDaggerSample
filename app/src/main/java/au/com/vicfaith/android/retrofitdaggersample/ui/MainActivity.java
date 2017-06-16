package au.com.vicfaith.android.retrofitdaggersample.ui;

import android.os.Bundle;

import au.com.vicfaith.android.retrofitdaggersample.R;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
    }
}