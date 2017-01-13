package au.com.vicfaith.android.retrofitdaggersample.util;

import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;

public class TestDataFactory {
    public static CityListResponse createMockResponse() {
        CityListResponse response = new CityListResponse();
        response.setMessage("Sydney");
        return response;
    }
}
