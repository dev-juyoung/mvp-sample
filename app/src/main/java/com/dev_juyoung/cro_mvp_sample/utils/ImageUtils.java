package com.dev_juyoung.cro_mvp_sample.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by juyounglee on 2017. 11. 3..
 */

public class ImageUtils {
    public static void load(@NonNull Context context, @NonNull Integer resourceId, @NonNull ImageView targetView) {
        Glide
                .with(context)
                .load(resourceId)
                .centerCrop()
                .into(targetView);
    }
}
