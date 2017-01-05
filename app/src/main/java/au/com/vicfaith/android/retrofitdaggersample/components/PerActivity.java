package au.com.vicfaith.android.retrofitdaggersample.components;

/**
 * Created by dkang on 3/01/2017.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
