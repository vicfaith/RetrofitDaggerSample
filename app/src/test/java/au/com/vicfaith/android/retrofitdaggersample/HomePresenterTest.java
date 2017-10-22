package au.com.vicfaith.android.retrofitdaggersample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.io.IOException;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HomePresenterTest {
    @Mock
    HomeView homeView;

    @Mock
    ApiInterface apiInterface;

    CityListResponse expectedResult = TestDataFactory.createMockResponse();

    @Before
    public void setup() {
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
    public void shouldReceiveObservable() {
        Observable<CityListResponse> observable = Observable.just(expectedResult);
        TestSubscriber<CityListResponse> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Collections.singletonList(expectedResult));
    }

    @Test
    public void shouldHaveCorrectDataWhenApiSucceed() {
        ApiService apiService = new ApiService(apiInterface);
        when(apiInterface.getCityList()).thenReturn(Observable.just(expectedResult));
        HomePresenter homePresenter = new HomePresenter(apiService, null);
        homePresenter.attachView(homeView);
        homePresenter.getCityList();

        verify(homeView).showProgressBar();
        verify(homeView).hideProgressBar();
        verify(homeView).showCityList(expectedResult);
        verify(homeView, never()).showError(null);
    }

    @Test
    public void shouldNotShowProgressWheRuntimeError() {
        ApiService apiService = new ApiService(apiInterface);
        when(apiInterface.getCityList()).thenReturn(Observable.<CityListResponse>error(new Exception("error")));
        HomePresenter homePresenter = new HomePresenter(apiService, null);
        homePresenter.attachView(homeView);
        homePresenter.getCityList();

        verify(homeView).showProgressBar();
        verify(homeView).hideProgressBar();
        verify(homeView).showError(any(String.class));
        verify(homeView, never()).showCityList(expectedResult);
    }

    @Test
    public void shouldNotShowProgressWhenApiFails() {
        ApiService apiService = new ApiService(apiInterface);
        when(apiInterface.getCityList()).thenReturn(Observable.<CityListResponse>error(new IOException("network unavailable")));
        HomePresenter homePresenter = new HomePresenter(apiService, null);
        homePresenter.attachView(homeView);
        homePresenter.getCityList();

        verify(homeView, times(1)).showProgressBar();
        verify(homeView, times(1)).hideProgressBar();
        ArgumentCaptor<String> captor = forClass(String.class);
        verify(homeView).showError(captor.capture());
        assertThat(captor.getValue(), is("network unavailable"));
        verify(homeView, never()).showCityList(expectedResult);
    }
}