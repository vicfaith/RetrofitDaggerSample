package au.com.vicfaith.android.retrofitdaggersample.di.modules;

import javax.inject.Singleton;

import au.com.vicfaith.android.retrofitdaggersample.network.ApiInterface;
import au.com.vicfaith.android.retrofitdaggersample.network.ApiService;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class TestNetworkModule {

    @Provides
    @Singleton
    ApiInterface providesApiInterface() {
        return mock(ApiInterface.class);
    }

    @Provides
    @Singleton
    ApiService providesApiService(ApiInterface apiInterface) {
        return new ApiService(apiInterface);
    }
}