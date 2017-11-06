package com.dev_juyoung.cro_mvp_sample;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dev_juyoung.cro_mvp_sample.base.BaseActivity;
import com.dev_juyoung.cro_mvp_sample.data.ImageData;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View {
    private static final String TAG = "MainActivity";

    private MainContract.Presenter mPresenter;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.imageList)
    RecyclerView imageList;

    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupPresenter();
        setupRefreshLayout();
        setupRecyclerView();
    }

    private void setupPresenter() {
        // 현재 View에서 사용할 Presenter 생성.
        mPresenter = new MainPresenter();

        // Presenter에 View를 setup.
        mPresenter.setView(this);

        // Adapter를 생성하고, Presenter에 Adapter 관련 View / Model setup.
        mAdapter = new ImageAdapter(this);
        mPresenter.setAdapterView(mAdapter);
        mPresenter.setAdapterModel(mAdapter);

        // Presenter에서 사용될 ImageData setup.
        mPresenter.setImageData(ImageData.getInstance());
    }

    private void setupRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Presenter로 데이터 요청 이벤트 전달.
                mPresenter.updateData(MainActivity.this, true);
            }
        });
    }

    private void setupRecyclerView() {
        imageList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        imageList.setHasFixedSize(true);
        imageList.setAdapter(mAdapter);

        // Presenter로 데이터 요청 이벤트 전달.
        mPresenter.updateData(this, false);
    }

    @Override
    public void updateRefresh() {
        Log.i(TAG, "Presenter -> View: UI 갱신 요청 이벤트.");

        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }
}
