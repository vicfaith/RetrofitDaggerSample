package au.com.vicfaith.android.retrofitdaggersample.network;

import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;
import retrofit2.http.GET;
import rx.Observable;

public interface ApiInterface {

    @GET("v1/city")
    Observable<CityListResponse> getCityList();

}