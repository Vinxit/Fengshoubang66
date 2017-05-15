package com.mingpin.fengshoubang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.mingpin.fengshoubang.base.activities.BaseActivity;
import com.mingpin.fengshoubang.box.BoxFragment;
import com.mingpin.fengshoubang.community.CommunityFragment;
import com.mingpin.fengshoubang.news.widget.NewsFragment;
import com.mingpin.fengshoubang.product.ProductFragment;
import com.mingpin.fengshoubang.user.UserFragment;

import java.util.ArrayList;


/**
 *
 */

public class MainActivity extends BaseActivity{

    private ArrayList<Fragment> fragments;
    private Toolbar toolbar;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
        }

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        //Mode有三种模式  MODE_FIXED模式 未选中的Item会显示文字 没有换挡动画
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //背景设置 有三种风格  对应 Mode使用
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //设置默认颜色
        bottomNavigationBar
                .setActiveColor("#399035")
                .setInActiveColor("#666666")
                .setBarBackgroundColor("#EEEEEE");
        //Badge角标设置
        BadgeItem numberBadgeItem =new BadgeItem()
                .setBorderWidth(2)   //角标的边界宽度
                .setBorderColor("#FF0000")//Badge的Border颜色
                .setBackgroundColor("#9ACD32")//Badge背景颜色
                .setGravity(Gravity.RIGHT|Gravity.TOP) //位置，默认右上角
                .setText("66")//显示的文本
                .setTextColor("#F0F8FF")//文本颜色
                .setAnimationDuration(2000)
                .setHideOnSelect(true);//当选中状态时消失，非选中状态显示
        //设置底部导航栏条目图标及文字
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.product_2,"首页"))
                .addItem(new BottomNavigationItem(R.drawable.news_2,"资讯"))
                .addItem(new BottomNavigationItem(R.drawable.community_2,"社区"))
                .addItem(new BottomNavigationItem(R.drawable.box_2,"发现"))
                .addItem(new BottomNavigationItem(R.drawable.user_2,"我的"))
                .setFirstSelectedPosition(1) //设置默认的tab
                .initialise();
             //底部导航栏设置切换监听
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {  //未选中 到 选中
                getSupportFragmentManager().beginTransaction().replace(R.id.layFrame,fragments.get(position)).commit();
            }
            @Override
            public void onTabUnselected(int position) {  //选中 到 未选中
            }
            @Override
            public void onTabReselected(int position) {  // 选中 到 再次选中
            }
        });
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        fragments = getFragments();
        //设置默认的Fargment
        getSupportFragmentManager().beginTransaction().replace(R.id.layFrame,NewsFragment.newInstance("资讯")).commit();
    }


    private ArrayList<Fragment> getFragments(){
        ArrayList fragments = new ArrayList<>();
        fragments.add(ProductFragment.newInstance("产品汇"));
        fragments.add(NewsFragment.newInstance("资讯"));
        fragments.add(CommunityFragment.newInstance("社区"));
        fragments.add(BoxFragment.newInstance("发现"));
        fragments.add(UserFragment.newInstance("个人中心"));
        return fragments;
    }
}
