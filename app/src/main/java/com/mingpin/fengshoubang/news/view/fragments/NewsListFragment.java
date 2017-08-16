package com.mingpin.fengshoubang.news.view.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingpin.fengshoubang.R;
import com.mingpin.fengshoubang.bean.NewsListItem;
import com.mingpin.fengshoubang.common.config.FsbApi;
import com.mingpin.fengshoubang.news.NewsContract;
import com.mingpin.fengshoubang.news.adapter.NewslistAdapter;
import com.mingpin.fengshoubang.news.presenter.NewsPresenter;
import com.mingpin.fengshoubang.news.view.NewsDetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener ,NewsContract.View{

    private static final String TAG = "NewsListFragment";
    private static final String TYPE = "type";
    private int mType = FsbApi.NEWS_TYPE_HOT;

    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private NewslistAdapter mAdapter;
    private List<NewsListItem> mData;
    private LinearLayoutManager mLayoutManager;
    private NewsContract.Persenter mNewsPresenter;
    private int pageIndex = 1;

    public NewsListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NewsListFragment newInstance(int type) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsPresenter = new NewsPresenter(this);
        mType = getArguments().getInt(TYPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list,null);
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.green,R.color.green);
        mSwipeRefreshWidget.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new NewslistAdapter(getActivity().getApplicationContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(mOnScrollListener);
        onRefresh();
        return view;
    }
    /*上拉加载监听*/
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            Log.i(TAG,"lastVisibleItem-------"+lastVisibleItem);
        }
        /**
         *
         * @param recyclerView
         * @param newState 目前的状态
         */
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                //加载更多
                Log.i(TAG, "lastVisibleItem: "+lastVisibleItem+"--getItemCount"+mAdapter.getItemCount());
                Log.i(TAG, "加载: "+pageIndex);
                mNewsPresenter.loadNews(mType, pageIndex);
            }
        }
    };
     /*条目点击监听*/
    private NewslistAdapter.OnItemClickListener mOnItemClickListener = new NewslistAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View view, int position) {
            if (mData.size()<0){
                return;
            }
            NewsListItem newsListItem = mAdapter.getItem(position);
            NewsDetailsActivity.show(getContext(),newsListItem.getId());
        }
    };

    @Override
    public void showProgress() {

    }
    @Override
    public void setPresenter(NewsContract.Persenter presenter) {

    }
    @Override
    public void addNews(List<NewsListItem> newsList) {
        mAdapter.isShowFooter(true);
        if(mData == null) {
            mData = new ArrayList<NewsListItem>();
        }
        mData.addAll(newsList);
        if(pageIndex == 1) {
            mAdapter.setmDate(mData);
        } else {
            //如果没有更多数据了,则隐藏footer布局
            if(newsList == null || newsList.size() == 0) {
                mAdapter.isShowFooter(false);
            }
            mAdapter.notifyDataSetChanged();
        }
        pageIndex++;
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
        if(pageIndex == 0) {
            mAdapter.isShowFooter(false);
            mAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? mRecyclerView.getRootView() : getActivity().findViewById(R.id.activity_main);
        Snackbar.make(view, getString(R.string.load_fail), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        if(mData != null) {
            mData.clear();
        }
        Log.i(TAG, "刷新: "+pageIndex);
        mNewsPresenter.loadNews(mType, pageIndex);
    }

}
