package au.com.vicfaith.android.retrofitdaggersample;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiInterface;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;
import au.com.vicfaith.android.retrofitdaggersample.ui.HomePresenter;
import au.com.vicfaith.android.retrofitdaggersample.ui.HomeView;
import au.com.vicfaith.android.retrofitdaggersample.util.RxSchedulersOverrideRule;
import au.com.vicfaith.android.retrofitdaggersample.util.TestDataFactory;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomePresenterDaggerTest {

    private HomePresenter homePresenter;

    private CityListResponse mockResponse = TestDataFactory.createMockResponse();

    private HomeView homeView = mock(HomeView.class);

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        ApiInterface apiInterface = mock(ApiInterface.class);
        when(apiInterface.getCityList()).thenReturn(Observable.just(mockResponse));
        ApiService apiService = new ApiService(apiInterface);
        homePresenter = new HomePresenter(apiService, null);
        homePresenter.attachView(homeView);
    }

    @Test
    public void shouldReceiveObservable() {
        Observable<CityListResponse> observable = Observable.just(mockResponse);
        TestSubscriber<CityListResponse> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertValue(mockResponse);
    }

    @Test
    public void shouldHaveCorrectDataWhenApiSucceed() {
        homePresenter.getCityList();
        verify(homeView).showProgressBar();
        verify(homeView).hideProgressBar();
        verify(homeView).showCityList(mockResponse);
    }

    @Test
    public void checkIfViewIsDetachedOnDestroyView() {
        homePresenter.detachView();
        assertFalse(homePresenter.isViewAttached());
    }
}