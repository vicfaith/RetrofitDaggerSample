package au.com.vicfaith.android.retrofitdaggersample.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import au.com.vicfaith.android.retrofitdaggersample.MyApplication;
import au.com.vicfaith.android.retrofitdaggersample.R;
import au.com.vicfaith.android.retrofitdaggersample.models.CityListData;
import au.com.vicfaith.android.retrofitdaggersample.models.CityListResponse;
import au.com.vicfaith.android.retrofitdaggersample.modules.HomePresenterModule;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dkang on 11/01/2017.
 */

public class MainFragment extends Fragment implements HomeView {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.progress)
    ProgressBar progressBar;

    @Inject
    HomePresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getInstance().getComponent().plus(new HomePresenterModule(this)).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();

        presenter.getCityList();
    }

    public void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCityList(CityListResponse response) {
        HomeAdapter adapter = new HomeAdapter(getActivity(), response.getData(),
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CityListData Item) {
                        Toast.makeText(getActivity(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}
