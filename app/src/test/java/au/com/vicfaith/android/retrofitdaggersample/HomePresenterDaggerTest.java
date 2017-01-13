package au.com.vicfaith.android.retrofitdaggersample;

import android.content.Context;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import au.com.vicfaith.android.retrofitdaggersample.components.DaggerTestMyAppComponent;
import au.com.vicfaith.android.retrofitdaggersample.components.TestMyAppComponent;
import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;
import au.com.vicfaith.android.retrofitdaggersample.modules.HomePresenterModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.MyAppModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.TestDataModule;
import au.com.vicfaith.android.retrofitdaggersample.modules.TestNetworkModule;
import au.com.vicfaith.android.retrofitdaggersample.ui.HomePresenter;
import au.com.vicfaith.android.retrofitdaggersample.ui.HomeView;
import au.com.vicfaith.android.retrofitdaggersample.util.RxSchedulersOverrideRule;
import au.com.vicfaith.android.retrofitdaggersample.util.TestDataFactory;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HomePresenterDaggerTest {
    TestMyAppComponent myAppComponent;

    @Inject
    HomePresenter homePresenter;

    CityListResponse mockResponse = TestDataFactory.createMockResponse();

    HomeView homeView = mock(HomeView.class);

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        myAppComponent = DaggerTestMyAppComponent.builder()
                .myAppModule(new MyAppModule(mock(MyApplication.class)))
                .dataModule(new TestDataModule(mock(Context.class)))
                .networkModule(new TestNetworkModule(null, mockResponse))
                .build();

        myAppComponent.plus(new HomePresenterModule(homeView)).inject(this);
    }

    @Test
    public void testObservable() {
        Observable<CityListResponse> observable = Observable.just(mockResponse);
        TestSubscriber<CityListResponse> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertValue(mockResponse);
    }

    @Test
    public void testHomePresenter() {
        homePresenter.getCityList();
        verify(homeView).showProgressBar();
        verify(homeView).hideProgressBar();
        verify(homeView).getCityListSuccess(mockResponse);
    }
}