package com.mingpin.fengshoubang.news;

import android.content.Intent;
import android.os.Bundle;
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
import com.mingpin.fengshoubang.commons.Urls;
import com.mingpin.fengshoubang.news.adapter.NewslistAdapter;
import com.mingpin.fengshoubang.news.bean.NewsListItem;
import com.mingpin.fengshoubang.news.presenter.NewsPresenter;
import com.mingpin.fengshoubang.news.presenter.NewsPresenterImpl;
import com.mingpin.fengshoubang.news.view.NewsView;

import java.util.ArrayList;
import java.util.List;


public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener ,NewsView{

    private static final String TAG = "NewsListFragment";
    private static final String TYPE = "type";
    private int mType = NewsFragment.NEWS_TYPE_HOT;

    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private NewslistAdapter mAdapter;
    private List<NewsListItem> mData;
    private LinearLayoutManager mLayoutManager;
    private NewsPresenter mNewsPresenter;
    private int pageIndex = 0;

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
        mNewsPresenter = new NewsPresenterImpl(this);
        if (getArguments() != null) {
            mType = getArguments().getInt(TYPE);
        }
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
    /*下拉加载监听*/
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount()
                    && mAdapter.isShowFooter()) {
                //加载更多
                mNewsPresenter.loadNews(mType, pageIndex + Urls.PAGE_SIZE);
            }
        }
    };
     /*条目点击*/
    private NewslistAdapter.OnItemClickListener mOnItemClickListener = new NewslistAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View view, int position) {
            if (mData.size()<0){
                return;
            }
            NewsListItem newsListItem = mAdapter.getItem(position);
            Intent intent = new Intent(getActivity(),NewsDetailsActivity.class);
            intent.putExtra("news",newsListItem);

        }
    };

    @Override
    public void showProgress() {

    }
    @Override
    public void addNews(List<NewsListItem> newsList) {
        mAdapter.isShowFooter(true);
        if(mData == null) {
            mData = new ArrayList<NewsListItem>();
            Log.i(TAG,"DATA:"+mData);
        }
        Log.i(TAG,"DATA:"+mData);
        mData.addAll(newsList);
        if(pageIndex == 0) {
            mAdapter.setmDate(mData);
        } else {
            //如果没有更多数据了,则隐藏footer布局
            if(newsList == null || newsList.size() == 0) {
                mAdapter.isShowFooter(false);
            }
            mAdapter.notifyDataSetChanged();
        }
        pageIndex += Urls.PAGE_SIZE;
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoadFailMsg() {

    }

    @Override
    public void onRefresh() {

    }

}
