package au.com.vicfaith.android.retrofitdaggersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    au.com.vicfaith.android.retrofitdaggersample.NetworkApi networkApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MyApplication) getApplication()).getComponent().inject(this);

        boolean injected =  networkApi == null ? false : true;
        TextView userAvailable = (TextView) findViewById(R.id.text);
        userAvailable.setText("Dependency injection worked: " + String.valueOf(injected));
    }
}