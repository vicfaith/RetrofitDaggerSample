package au.com.vicfaith.android.retrofitdaggersample.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import au.com.vicfaith.android.retrofitdaggersample.MyApplication;
import au.com.vicfaith.android.retrofitdaggersample.R;
import au.com.vicfaith.android.retrofitdaggersample.components.DaggerMyActivityComponent;
import au.com.vicfaith.android.retrofitdaggersample.components.MyActivityComponent;
import au.com.vicfaith.android.retrofitdaggersample.models.CityListData;
import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;
import au.com.vicfaith.android.retrofitdaggersample.modules.HomePresenterModule;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HomeView {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.progress)
    ProgressBar progressBar;

    HomePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MyActivityComponent component = DaggerMyActivityComponent.builder()
                .myAppComponent(((MyApplication) getApplication()).getComponent())
                .homePresenterModule(new HomePresenterModule(((MyApplication) getApplication()).getComponent().getApiService(), this))
                .build();
        component.inject(this);

        apiService = component.getApiService();
        sharedPreferences = component.getSharedPreferences();
        presenter = component.getHomePresenter();

        initViews();

        presenter.getCityList();
    }

    public void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCityListSuccess(CityListResponse response) {
        HomeAdapter adapter = new HomeAdapter(getApplicationContext(), response.getData(),
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CityListData Item) {
                        Toast.makeText(getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}