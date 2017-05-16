package com.mingpin.fengshoubang.base.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.mingpin.fengshoubang.R;

/**
 * Created by Vinxit_HK on 2017/5/16.
 */

public abstract class BaseViewPagerFragment extends BaseTitleFragment {

    protected TabLayout mTabNav;

    protected ViewPager mBaseViewPager;
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_base_viewpager;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mTabNav = (TabLayout) root.findViewById(R.id.tab_nav);
        mBaseViewPager = (ViewPager) root.findViewById(R.id.base_viewpager);
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(getChildFragmentManager(),getPagers());
        mBaseViewPager.setAdapter(adapter);
        mTabNav.setupWithViewPager(mBaseViewPager);
        mBaseViewPager.setCurrentItem(0,true);
    }
    protected abstract PagerInfo[] getPagers();

    public static class PagerInfo {
        private String title;
        private Fragment fragment;

        public PagerInfo(String title, Fragment fragment) {
            this.title = title;
            this.fragment = fragment;
        }
    }

    public class BaseViewPagerAdapter extends FragmentPagerAdapter{
        private PagerInfo[] mPagerInfos;
        private Fragment mFragment;

        public BaseViewPagerAdapter(FragmentManager fm,PagerInfo[] pagerInfos) {
            super(fm);
            mPagerInfos = pagerInfos;
        }

        /**
         * 通过调用此方法来通知PageAdapter当前ViewPager显示的主要项，提供用户对主要项进行操作的方法
         * @param container viewpager本身
         * @param position 下标位置
         * @param object
         */
        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            if(object instanceof Fragment){
                mFragment = (Fragment) object;
            }
        }

        public Fragment getmFragment(){
            return mFragment;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mPagerInfos[position].title;
        }

        @Override
        public Fragment getItem(int position) {
            return mPagerInfos[position].fragment;
        }

        @Override
        public int getCount() {
            return mPagerInfos.length;
        }

    }

}
