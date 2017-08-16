package com.mingpin.fengshoubang.base.detail;

import com.mingpin.fengshoubang.base.BasePresenter;
import com.mingpin.fengshoubang.base.BaseView;

/**
 * Created by Vinxit_HK on 2017/6/26.
 */

public interface DetailContract {


    interface EmptyView{
    }


    interface View extends BaseView<Presenter>{
        void showGetDetailSuccess();
        void showFavSuccess();
        void showFavError();
        void showConmentSuccess();
        void showConmentError();
        void showShareSuccess();
    }

    interface Presenter extends BasePresenter{
        void getCache();
        void getDetail();
        void favReverse();
        void addComment();
    }
}
