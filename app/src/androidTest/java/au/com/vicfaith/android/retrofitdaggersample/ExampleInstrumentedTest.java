package au.com.vicfaith.android.retrofitdaggersample;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import au.com.vicfaith.android.retrofitdaggersample.di.components.DaggerTestMyAppComponent;
import au.com.vicfaith.android.retrofitdaggersample.di.components.TestMyAppComponent;
import au.com.vicfaith.android.retrofitdaggersample.di.modules.TestDataModule;
import au.com.vicfaith.android.retrofitdaggersample.di.modules.TestNetworkModule;
import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiInterface;
import au.com.vicfaith.android.retrofitdaggersample.ui.MainActivity;
import au.com.vicfaith.android.retrofitdaggersample.ui.MainFragment;
import rx.Observable;

import static org.mockito.Mockito.when;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Inject
    ApiInterface mApiInterface;

    @Before
    public void setUp() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        TestMyAppComponent appComponent = DaggerTestMyAppComponent.builder()
                .testDataModule(new TestDataModule(appContext))
                .testNetworkModule(new TestNetworkModule())
                .build();

        appComponent.inject(this);

        MyApplication.getInstance().setAppComponent(appComponent);
    }

    @Test
    public void testMockData() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        // given
        CityListResponse response = new CityListResponse();
        response.setMessage("Sydney");

        // when
        when(mApiInterface.getCityList()).thenReturn(Observable.just(response));

        // then
        Intent intent = new Intent(appContext, MainActivity.class);
        mActivityRule.launchActivity(intent);

        MainFragment fragment = (MainFragment) mActivityRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
    }
}
