package au.com.vicfaith.android.retrofitdaggersample.modules;

import javax.inject.Singleton;

import au.com.vicfaith.android.retrofitdaggersample.NetworkApi;
import dagger.Module;
import dagger.Provides;

@Module
public class NetworkApiModule {
    @Provides
    @Singleton
    public NetworkApi getNetwork(){
        return new NetworkApi();
    }
}