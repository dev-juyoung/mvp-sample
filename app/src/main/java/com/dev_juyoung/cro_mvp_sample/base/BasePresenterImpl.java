package com.dev_juyoung.cro_mvp_sample.base;

/**
 * Created by juyounglee on 2017. 11. 8..
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {
    protected T view;

    @Override
    public void setView(T view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        view = null;
    }
}
