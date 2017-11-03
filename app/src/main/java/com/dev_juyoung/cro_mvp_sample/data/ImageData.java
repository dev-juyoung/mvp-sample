package com.dev_juyoung.cro_mvp_sample.data;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by juyounglee on 2017. 11. 3..
 */

public class ImageData {
    private static final String TAG = "ImageData";
    private static ImageData instance;

    public static ImageData getInstance() {
        if (instance == null) {
            instance = new ImageData();
        }
        return instance;
    }

    public ArrayList<Integer> getImages(Context context, int size) {
        ArrayList<Integer> items = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int randomIndex = (int) (Math.random() * 30) + 1;
            String resourceName = String.format("resource_%02d", randomIndex);
            Log.i(TAG, resourceName);

            int resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getApplicationContext().getPackageName());
            items.add(resourceId);
        }

        return items;
    }
}