package com.dev_juyoung.cro_mvp_sample;

import android.content.Context;

import com.dev_juyoung.cro_mvp_sample.data.ImageRepository;

/**
 * Created by juyounglee on 2017. 11. 6..
 */

public interface MainContract {
    interface View {
        void updateRefresh();
        void showToast(String message);
    }

    interface Presenter {
        void setView(View view);
        void setAdapterView(ImageAdapterContract.View adapterView);
        void setAdapterModel(ImageAdapterContract.Model adapterModel);
        void setImageData(ImageRepository repository);
        void updateData(Context context, boolean isUpdate);
    }
}
