package com.dev_juyoung.cro_mvp_sample;

/**
 * Created by juyounglee on 2017. 11. 6..
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }
}
