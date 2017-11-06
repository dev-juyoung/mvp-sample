package com.dev_juyoung.cro_mvp_sample.data;

import android.content.Context;
import android.util.Log;

/**
 * Created by juyounglee on 2017. 11. 6..
 */

public class ImageRepository implements ImageSource {
    private static final String TAG = "ImageRepository";

    private static ImageRepository instance;

    private ImageLocalDataSource localDataSource;

    public static ImageRepository getInstance() {
        if (instance == null) {
            instance = new ImageRepository();
        }

        return instance;
    }

    public ImageRepository() {
        localDataSource = ImageLocalDataSource.getInstance();
    }


    @Override
    public void getImages(Context context, int size, LoadImageCallback callback) {
        Log.i(TAG, "Repository(Model): Presenter의 데이터 요청에 따른 로직 처리. [상황에 맞게 Local || Remote 데이터를 처리하도록 선택적으로 요청.]");

        localDataSource.getImages(context, size, callback);
    }
}
