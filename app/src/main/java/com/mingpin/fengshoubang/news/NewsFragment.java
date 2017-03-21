package com.mingpin.fengshoubang.news;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mingpin.fengshoubang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    public static final int NEWS_TYPE_HOT = 0;      //动态
    public static final int NEWS_TYPE_PRODUCT = 1;  //产品
    public static final int NEWS_TYPE_AT = 2;       //农技
    public static final int NEWS_TYPE_VIDEO = 3;    //视频

    private TabLayout mTablayout;   //news顶部导航栏
    private ViewPager mViewPager;   //不同栏目填充的view
    public NewsFragment() {
        // Required empty public constructor
    }
    public static NewsFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,null);
        mTablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        //设置预加载的viewpager个数，默认自动加载下一个视图
        mViewPager.setOffscreenPageLimit(2);
        setupViewPager(mViewPager);
        //设置顶部导航栏及文字
        mTablayout.addTab(mTablayout.newTab().setText(R.string.news_hot));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.news_product));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.news_at));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.news_video));
        mTablayout.setupWithViewPager(mViewPager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
        tv.setText(getArguments().getString("ARGS"));
    }
    private void setupViewPager(ViewPager mViewPager) {
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_HOT), getString(R.string.news_hot));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_PRODUCT), getString(R.string.news_product));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_AT), getString(R.string.news_at));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_VIDEO), getString(R.string.news_video));
        mViewPager.setAdapter(adapter);
    }
    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}
