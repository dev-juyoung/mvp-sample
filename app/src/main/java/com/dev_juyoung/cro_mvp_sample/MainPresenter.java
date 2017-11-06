package com.dev_juyoung.cro_mvp_sample;

import android.content.Context;
import android.util.Log;

import com.dev_juyoung.cro_mvp_sample.data.ImageData;
import com.dev_juyoung.cro_mvp_sample.utils.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by juyounglee on 2017. 11. 6..
 */

public class MainPresenter implements MainContract.Presenter, OnItemClickListener {
    private static final String TAG = "MainPresenter";

    private MainContract.View view;
    private ImageAdapterContract.View adapterView;
    private ImageAdapterContract.Model adapterModel;
    private ImageData imageData;

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void setAdapterView(ImageAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(this);
    }

    @Override
    public void setAdapterModel(ImageAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }

    @Override
    public void updateData(Context context, boolean isUpdate) {
        Log.i(TAG, "View -> Presenter: 데이터 갱신 요청 이벤트.");

        ArrayList<Integer> items = imageData.getImages(context, 10);

        // update 유무에 따른 adapterModel 갱신.
        if (!isUpdate) {
            adapterModel.addItems(items);
        } else {
            adapterModel.updateItems(items);
        }

        // adapterView UI 갱신 이벤트 전달.
        adapterView.updateView();
        // view에 UI 갱신 이벤트 전달.
        view.updateRefresh();
    }

    @Override
    public void onItemClick(int position) {
        Log.i(TAG, "AdapterView -> Presenter: 사용자 클릭 이벤트에 따른 로직 처리. [ model로 데이터 요청 or Activity 전환 등 ]");

        String formattedMessage = String.format("현재 선택된 Item의 Position: %d", position);
        view.showToast(formattedMessage);
    }
}
