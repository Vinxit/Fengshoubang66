package com.mingpin.fengshoubang.base.detail;

import com.mingpin.fengshoubang.base.fragments.BaseFragment;

/**
 * Created by Vinxit_HK on 2017/6/27.
 */

public abstract class DetailFragment extends BaseFragment implements DetailContract.View{

    protected DetailContract.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void showGetDetailSuccess() {

    }

    @Override
    public void showFavSuccess() {

    }

    @Override
    public void showFavError() {

    }

    @Override
    public void showConmentSuccess() {

    }

    @Override
    public void showConmentError() {

    }

    @Override
    public void showShareSuccess() {

    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
