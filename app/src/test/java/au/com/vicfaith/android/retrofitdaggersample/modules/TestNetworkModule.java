package au.com.vicfaith.android.retrofitdaggersample.modules;

import java.io.File;

import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiInterface;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;
import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestNetworkModule extends NetworkModule {

    CityListResponse response;

    public TestNetworkModule(File cacheFile, CityListResponse response) {
        super(cacheFile);
        this.response = response;
    }

    @Override
    ApiService providesApiService() {
        ApiInterface apiInterface = mock(ApiInterface.class);
        when(apiInterface.getCityList()).thenReturn(Observable.just(response));
        return new ApiService(apiInterface);
    }
}