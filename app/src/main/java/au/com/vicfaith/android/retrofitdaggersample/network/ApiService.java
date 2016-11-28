package au.com.vicfaith.android.retrofitdaggersample.network;

import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ApiService {
    private final ApiInterface apiInterface;

    public ApiService(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Subscription getCityList(final GetCityListCallback callback) {
        return apiInterface.getCityList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CityListResponse>>() {
                    @Override
                    public Observable<? extends CityListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CityListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);

                    }

                    @Override
                    public void onNext(CityListResponse cityListResponse) {
                        callback.onSuccess(cityListResponse);
                    }
                });
    }

    public interface GetCityListCallback {
        void onSuccess(CityListResponse cityListResponse);

        void onError(Throwable error);
    }
}