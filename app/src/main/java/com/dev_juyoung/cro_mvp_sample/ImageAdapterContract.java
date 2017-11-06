package com.dev_juyoung.cro_mvp_sample;

import com.dev_juyoung.cro_mvp_sample.utils.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by juyounglee on 2017. 11. 6..
 */

public interface ImageAdapterContract {
    interface View {
        void updateView();
        void setOnItemClickListener(OnItemClickListener onItemClickListener);
    }

    interface Model {
        void addItems(ArrayList<Integer> items);
        void updateItems(ArrayList<Integer> items);
    }
}
