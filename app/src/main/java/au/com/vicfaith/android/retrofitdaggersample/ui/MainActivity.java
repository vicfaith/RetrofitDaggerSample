package au.com.vicfaith.android.retrofitdaggersample.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import au.com.vicfaith.android.retrofitdaggersample.R;
import au.com.vicfaith.android.retrofitdaggersample.models.CityListData;
import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HomeView {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.progress)
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();

        HomePresenter presenter = new HomePresenter(apiService, this);
        presenter.getCityList();
    }

    public void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getCityListSuccess(CityListResponse cityListResponse) {
        HomeAdapter adapter = new HomeAdapter(getApplicationContext(), cityListResponse.getData(),
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CityListData Item) {
                        Toast.makeText(getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        recyclerView.setAdapter(adapter);
    }
}