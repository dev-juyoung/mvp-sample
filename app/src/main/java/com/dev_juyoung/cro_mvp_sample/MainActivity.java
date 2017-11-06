package com.dev_juyoung.cro_mvp_sample;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dev_juyoung.cro_mvp_sample.base.BaseActivity;
import com.dev_juyoung.cro_mvp_sample.data.ImageData;

import java.util.ArrayList;

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
        updateView(ImageData.getInstance().getImages(this, 10), false);
    }

    private void setupPresenter() {
        // 현재 View에서 사용할 Presenter를 생성한다.
        mPresenter = new MainPresenter();
        // Presenter에 View를 setup 한다.
        mPresenter.setView(this);
        // Presenter에서 사용될 ImageData를 setup 한다.
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
        mAdapter = new ImageAdapter(this);

        imageList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        imageList.setHasFixedSize(true);
        imageList.setAdapter(mAdapter);

        // Presenter로 데이터 요청 이벤트 전달.
        mPresenter.updateData(this, false);
    }

    @Override
    public void updateView(ArrayList<Integer> items, boolean isUpdate) {
        // refreshLayout의 progress가 돌아가고 있다면, 제거한다.
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }

        if (!isUpdate) {
            // 신규 추가라면 Adpater의 addItems 메서드 호출.
            mAdapter.addItems(items);
        } else {
            // 데이터 갱신이라면 Adapter의 updateItems 메서드 호출.
            mAdapter.updateItems(items);
        }
    }
}
