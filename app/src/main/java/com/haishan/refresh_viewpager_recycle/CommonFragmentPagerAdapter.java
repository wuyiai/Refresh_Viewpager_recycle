package com.haishan.refresh_viewpager_recycle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

/**
 * Created by ZL on 2018/1/19.
 * 一个通用的FragmentPagerAdapter    该 PagerAdapter 的实现将每一个生成的 Fragment 都将保存在内存之中
 * <p>
 * 作用：需要用到FragmentPagerAdapter的时候，只需要传不同参数即可，不用再重写一个类，该类中实现了共用的方法
 */

public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {
    Fragment[] data;
    String[] titles;

    /**
     * @param fm
     * @param data
     */
    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> data) {
        this(fm, data.toArray(new Fragment[data.size()]), null);
    }


    /**
     * @param fm
     * @param data
     */
    public CommonFragmentPagerAdapter(FragmentManager fm, Fragment[] data) {
        this(fm, data, null);
    }

    /**
     * @param fm
     * @param data
     * @param titles
     * @deprecated Use {@link #CommonFragmentPagerAdapter(FragmentManager, List)} instead.由于titles基本不会被用到
     */
    @Deprecated
    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> data, String[] titles) {
        this(fm, data.toArray(new Fragment[data.size()]), titles);
    }

    /**
     * @param fm
     * @param data
     * @param titles
     * @deprecated Use {@link #CommonFragmentPagerAdapter(FragmentManager, List)} instead.由于titles基本不会被用到
     */
    @Deprecated
    public CommonFragmentPagerAdapter(FragmentManager fm, Fragment[] data, String[] titles) {
        super(fm);
        this.data = data;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return null == data ? null : data[position];
    }

    @Override
    public int getCount() {
        return null == data ? 0 : data.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null == titles ? null : titles[position];
    }
}
