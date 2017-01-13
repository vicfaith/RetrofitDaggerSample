package au.com.vicfaith.android.retrofitdaggersample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.Collections;

import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiInterface;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;
import au.com.vicfaith.android.retrofitdaggersample.ui.HomePresenter;
import au.com.vicfaith.android.retrofitdaggersample.ui.HomeView;
import au.com.vicfaith.android.retrofitdaggersample.util.TestDataFactory;
import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class HomePresenterTest {
    @Mock
    HomeView homeView;

    ApiService apiService;
    CityListResponse expectedResult = TestDataFactory.createMockResponse();

    @Before
    public void setup() {
        apiService = new ApiService(new ApiInterface() {
            @Override
            public Observable<CityListResponse> getCityList() {
                return Observable.just(expectedResult);
            }
        });

        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });

        initMocks(this);
    }

    @After
    public void tearDown() {
        RxAndroidPlugins.getInstance().reset();
    }

    @Test
    public void testObservable() {
        Observable<CityListResponse> observable = Observable.just(expectedResult);
        TestSubscriber<CityListResponse> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Collections.singletonList(expectedResult));
    }

    @Test
    public void testHomePresenter() {
        HomePresenter homePresenter = new HomePresenter(apiService, null);
        homePresenter.attachView(homeView);
        homePresenter.getCityList();

        verify(homeView).showProgressBar();
        verify(homeView).hideProgressBar();
        verify(homeView).getCityListSuccess(expectedResult);
    }
}