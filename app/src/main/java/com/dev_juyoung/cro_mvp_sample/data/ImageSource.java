package com.dev_juyoung.cro_mvp_sample.data;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by juyounglee on 2017. 11. 6..
 */

public interface ImageSource {

    /**
     * Local 또는 Remote에서 데이터를 불러 온 후의 처리를 위한 Callback Methods.
     * 성공 / 실패 등 필요에 따라 Method는 생성한다.
     */
    interface LoadImageCallback {
        void onImageLoaded(ArrayList<Integer> items);
    }

    void getImages(Context context, int size, LoadImageCallback callback);
}