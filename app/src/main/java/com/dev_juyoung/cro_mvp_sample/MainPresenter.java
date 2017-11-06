package com.dev_juyoung.cro_mvp_sample;

import android.content.Context;

import com.dev_juyoung.cro_mvp_sample.data.ImageData;

import java.util.ArrayList;

/**
 * Created by juyounglee on 2017. 11. 6..
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private ImageData imageData;

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }

    @Override
    public void updateData(Context context, boolean isUpdate) {
        ArrayList<Integer> items = imageData.getImages(context, 10);
        view.updateView(items, isUpdate);
    }
}
