package com.dev_juyoung.cro_mvp_sample.base;

/**
 * Created by juyounglee on 2017. 11. 8..
 */

public interface BasePresenter<T extends BaseView> {
    void setView(T view);
    void removeView();
}
