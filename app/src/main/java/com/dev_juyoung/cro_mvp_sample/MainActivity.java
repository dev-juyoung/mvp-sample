package com.dev_juyoung.cro_mvp_sample;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dev_juyoung.cro_mvp_sample.base.BaseActivity;
import com.dev_juyoung.cro_mvp_sample.data.ImageData;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.imageList)
    RecyclerView imageList;

    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRefreshLayout();
        setupRecyclerView();
        updateView(ImageData.getInstance().getImages(this, 10), false);
    }

    private void setupRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateView(ImageData.getInstance().getImages(MainActivity.this, 10), true);
            }
        });
    }

    private void setupRecyclerView() {
        mAdapter = new ImageAdapter(this);

        imageList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        imageList.setHasFixedSize(true);
        imageList.setAdapter(mAdapter);
    }

    private void updateView(ArrayList<Integer> data, boolean isUpdate) {
        if (!isUpdate) {
            mAdapter.addItems(data);
        } else {
            if (refreshLayout.isRefreshing()) {
                refreshLayout.setRefreshing(false);
            }

            mAdapter.updateItems(data);
        }
    }
}
