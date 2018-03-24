package com.dev_juyoung.cro_mvp_sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dev_juyoung.cro_mvp_sample.base.BasePresenterImpl;
import com.dev_juyoung.cro_mvp_sample.data.ImageRepository;
import com.dev_juyoung.cro_mvp_sample.data.ImageSource;
import com.dev_juyoung.cro_mvp_sample.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by juyounglee on 2017. 11. 6..
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter, OnItemClickListener {
    private final int _TEST_LIST_COUNT = 10;
    private static final String TAG = "MainPresenter";

    private ImageAdapterContract.View adapterView;
    private ImageAdapterContract.Model adapterModel;
    private ImageRepository repository;

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

        repository.getImages(context, _TEST_LIST_COUNT)
                .doOnSuccess( addItems(isUpdate) )
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( updateView() );
    }

    @NonNull
    private Consumer<ArrayList<Integer>> updateView() {
        return new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> integers) throws Exception {
                // adapterView UI 갱신 이벤트 전달.
                adapterView.updateView();
                // view에 UI 갱신 이벤트 전달.
                view.updateRefresh();
            }
        };
    }

    @NonNull
    private Consumer<ArrayList<Integer>> addItems(final boolean isUpdate) {
        return new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> items) throws Exception {
                // update 유무에 따른 adapterModel 갱신.
                if (!isUpdate) {
                    adapterModel.addItems(items);
                } else {
                    adapterModel.updateItems(items);
                }
            }
        };
    }

    @Override
    public void onItemClick(int position) {
        Log.i(TAG, "Presenter: AdapterView로 부터 사용자 클릭 이벤트 전달 받음. [model 접근 또는 Activity 전환 등 추가 로직 처리] ");

        String formattedMessage = String.format("현재 선택된 Item의 Position: %d", position);
        view.showToast(formattedMessage);
    }
}
