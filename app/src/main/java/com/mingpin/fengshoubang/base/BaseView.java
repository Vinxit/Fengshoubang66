package com.mingpin.fengshoubang.base;

/**
 * Created by Vinxit_HK on 2017/6/26.
 */

public interface BaseView<Presenter extends BasePresenter> {
    void setPresenter(Presenter presenter);
}
