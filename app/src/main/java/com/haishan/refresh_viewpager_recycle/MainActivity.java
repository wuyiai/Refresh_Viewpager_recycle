package com.haishan.refresh_viewpager_recycle;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SmartRefreshLayout layout_refresh;
    private FillViewpager viewpager;
    private OneFragment oneFragment;
    private SlidingTabLayout slidingTabLayoutType;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private LoadingDialog loadingDialog;
    private View appBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout_refresh = findViewById(R.id.layout_refresh);
        viewpager = findViewById(R.id.viewpager);
        appBarLayout = findViewById(R.id.appBarLayout);
        slidingTabLayoutType = findViewById(R.id.slidingTabLayoutType);
        layout_refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (viewpager.getCurrentItem() == 0) {
                    oneFragment.requestData();
                } else if (viewpager.getCurrentItem() == 1) {
                    twoFragment.requestData();
                } else if (viewpager.getCurrentItem() == 2) {
                    threeFragment.requestData();
                }

                finishLoadData(viewpager.getCurrentItem());
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                layout_refresh.postDelayed()
                Toast.makeText(MainActivity.this, "刷新就不写逻辑了", Toast.LENGTH_SHORT).show();
                layout_refresh.finishRefresh(true);

            }
        });

        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();

        String[] titles = {"第一", "第二", "第三"};

        List<Fragment> list = new ArrayList<>();
        list.add(oneFragment);
        list.add(twoFragment);
        list.add(threeFragment);

        viewpager.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(), list));
        viewpager.setOffscreenPageLimit(list.size());
        slidingTabLayoutType.setViewPager(viewpager, titles);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(final int i) {
                if (viewpager.getCurrentItem() == 0 && oneFragment.mAdapter.mDateBeen.size() == 0) {
                    oneFragment.requestData();
                    Log.v("jiazaizhong", "oneFragment.requestData()");
                } else if (viewpager.getCurrentItem() == 1 && twoFragment.mAdapter.mDateBeen.size() == 0) {
                    twoFragment.requestData();
                    Log.v("jiazaizhong", "twoFragment.requestData()");
                } else if (viewpager.getCurrentItem() == 2 && threeFragment.mAdapter.mDateBeen.size() == 0) {
                    threeFragment.requestData();
                    Log.v("jiazaizhong", "threeFragment.requestData()");
                }
                finishLoadData(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        viewpager.postDelayed(new Runnable() {
            @Override
            public void run() {
                oneFragment.requestData();

            }
        }, 500);

        finishLoadData(0);

    }


    public void finishLoadData(final int position) {

        showLoadingDialog();

        layout_refresh.finishLoadMore(true);

        layout_refresh.postDelayed(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < viewpager.mHeightList.size(); i++) {
                    Log.v("jiazaizhong", i + ":" + viewpager.mHeightList.get(i));
                }

                ViewGroup.LayoutParams layoutParams = viewpager.getLayoutParams();
                Log.v("jiazaizhong", "layoutParams.height:" + layoutParams.height);
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams.height = viewpager.mHeightList.get(viewpager.getCurrentItem())+1600;
                Log.v("jiazaizhong", "当前item:" + viewpager.getCurrentItem());
                Log.v("jiazaizhong", "当前高度" + layoutParams.height);
                viewpager.setLayoutParams(layoutParams);
                hideLoadingDialog();
            }
        }, 500);
    }


    public void showLoadingDialog() {
        if (loadingDialog == null && !isFinishing())
            loadingDialog = LoadingDialog.create(this).setCancelable(false).show();
    }

    public void hideLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

}
