package com.dev_juyoung.cro_mvp_sample;

import android.content.Context;

import com.dev_juyoung.cro_mvp_sample.data.ImageData;

import java.util.ArrayList;

/**
 * Created by juyounglee on 2017. 11. 6..
 */

public interface MainContract {
    interface View {
        void updateView(ArrayList<Integer> items, boolean isUpdate);
    }

    interface Presenter {
        void setView(View view);
        void setImageData(ImageData imageData);
        void updateData(Context context, boolean isUpdate);
    }
}
