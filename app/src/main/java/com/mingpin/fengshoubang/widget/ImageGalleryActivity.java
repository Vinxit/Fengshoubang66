package com.mingpin.fengshoubang.widget;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingpin.fengshoubang.R;
import com.mingpin.fengshoubang.common.utils.ImageLoaderUtils;


public class ImageGalleryActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    public static final String KEY_IMAGE = "images";
    public static final String KEY_COOKIE = "cookie_need";
    public static final String KEY_POSITION = "position";
    public static final String KEY_NEED_SAVE = "save";
    private String[] mImageSources;
    private String imagePath;
    private int mCurPosition = 0;
    private Context mContext;
    private boolean mNeedSaveLocal;
    private boolean mNeedCookie;
    private PreviewerViewPager mImagePager;
    private TextView tv_index;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        imagePath = getIntent().getStringExtra("image");

        mImagePager = (PreviewerViewPager) findViewById(R.id.vp_image);
        tv_index = (TextView) findViewById(R.id.tv_index);
        tv_index.setText("haha");
        mImagePager.addOnPageChangeListener(this);
        mImagePager.setAdapter(new ViewPagerAdapter());
/*        mImagePager.setCurrentItem(mCurPosition);*/
        onPageSelected(mCurPosition);
    }

/*
    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected boolean initBundle(Bundle bundle) {
        mImageSources = bundle.getStringArray(KEY_IMAGE);
        mCurPosition = bundle.getInt(KEY_POSITION, 0);
        mNeedSaveLocal = bundle.getBoolean(KEY_NEED_SAVE, true);
        mNeedCookie = bundle.getBoolean(KEY_COOKIE, false);
        return false;

    }

    @Override
    protected void initWindow() {
        super.initWindow();
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
    }*/


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurPosition = position;
        tv_index.setText(String.format("%s/%s",(position+1),1));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class ViewPagerAdapter extends PagerAdapter implements ImagePreviewView.OnReachBorderListener{
        private View.OnClickListener mFinishClickListener;
        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return true;
        }

        @Override
        public void onReachBorder(boolean isReached) {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.lay_gallery_page_item,container,false);
            ImagePreviewView previewView = (ImagePreviewView) view.findViewById(R.id.iv_preview);
            ImageLoaderUtils.display(container.getContext(),previewView,imagePath);
            previewView.setOnReachBorderListener(this);
            previewView.setOnClickListener(getListener());
            container.addView(view);
            return view;
        }
        private View.OnClickListener getListener(){
            if(mFinishClickListener == null){
                mFinishClickListener = new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                };
            }
            return mFinishClickListener;
        }
    }

}
