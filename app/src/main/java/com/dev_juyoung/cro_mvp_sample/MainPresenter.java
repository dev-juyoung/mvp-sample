package com.dev_juyoung.cro_mvp_sample;

import android.content.Context;
import android.util.Log;

import com.dev_juyoung.cro_mvp_sample.data.ImageRepository;
import com.dev_juyoung.cro_mvp_sample.data.ImageSource;
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
    private ImageRepository repository;

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
    public void setImageData(ImageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateData(Context context, final boolean isUpdate) {
        Log.i(TAG, "Presenter: View로 부터 데이터 갱신 요청.");

        repository.getImages(context, 10, new ImageSource.LoadImageCallback() {
            @Override
            public void onImageLoaded(ArrayList<Integer> items) {
                Log.i(TAG, "Presenter: Callback을 통해 Model에서 처리된 데이터를 전달 받음.");

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
        });
    }

    @Override
    public void onItemClick(int position) {
        Log.i(TAG, "Presenter: AdapterView로 부터 사용자 클릭 이벤트 전달 받음. [model 접근 또는 Activity 전환 등 추가 로직 처리] ");

        String formattedMessage = String.format("현재 선택된 Item의 Position: %d", position);
        view.showToast(formattedMessage);
    }
}
