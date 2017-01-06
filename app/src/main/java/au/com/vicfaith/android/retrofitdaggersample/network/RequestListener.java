package au.com.vicfaith.android.retrofitdaggersample.network;

import rx.Subscriber;

public class RequestListener<RESULT> {
    Subscriber<RESULT> subscriber = new Subscriber<RESULT>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            onRequestError(e);
        }

        @Override
        public void onNext(RESULT response) {
            onRequestSuccess(response);
        }
    };

    public void onRequestSuccess(RESULT response) {
    }

    public void onRequestError(Throwable e) {
    }
}
