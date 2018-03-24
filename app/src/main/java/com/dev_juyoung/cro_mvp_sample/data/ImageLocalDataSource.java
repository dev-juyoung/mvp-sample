package com.dev_juyoung.cro_mvp_sample.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;

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
    public Single<ArrayList<Integer>> getImages(final Context context, int size) {
        Log.i(TAG, "LocalDataSource(Model): Repository의 요청에 따라 Local 저장소에서 요청된 데이터를 처리함.");

        return Observable.range(0,size)
                    .map(integer -> convertData(context))
                    .toList()
                    .map(ArrayList::new);
    }

    @NonNull
    private Integer convertData(Context context) {
        int randomIndex = (int) (Math.random() * 30) + 1;
        String resourceName = String.format("resource_%02d", randomIndex);
        return context.getResources().getIdentifier(resourceName, "drawable", context.getApplicationContext().getPackageName());
    }
}
