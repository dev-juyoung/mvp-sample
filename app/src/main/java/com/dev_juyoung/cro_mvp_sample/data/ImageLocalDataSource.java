package com.dev_juyoung.cro_mvp_sample.data;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by juyounglee on 2017. 11. 6..
 */

public class ImageLocalDataSource implements ImageSource {
    private static final String TAG = "ImageLocalDataSource";

    private static ImageLocalDataSource instance;

    public static ImageLocalDataSource getInstance() {
        if (instance == null) {
            instance = new ImageLocalDataSource();
        }

        return instance;
    }

    @Override
    public void getImages(Context context, int size, LoadImageCallback callback) {
        Log.i(TAG, "LocalDataSource(Model): Repository의 요청에 따라 Local 저장소에서 요청된 데이터를 처리함.");

        ArrayList<Integer> items = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int randomIndex = (int) (Math.random() * 30) + 1;
            String resourceName = String.format("resource_%02d", randomIndex);
            Log.i(TAG, "데이터 생성: " + resourceName);

            int resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getApplicationContext().getPackageName());
            items.add(resourceId);
        }

        if (callback != null) {
            callback.onImageLoaded(items);
        }
    }
}
