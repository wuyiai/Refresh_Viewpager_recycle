package com.haishan.refresh_viewpager_recycle;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *  ScrollView嵌套ViewPager不显示和出现空白部分
 * */
public class FillViewpager extends ViewPager {

    public List<Integer> mHeightList = new ArrayList<>();

    public FillViewpager(Context context) {
        super(context);
    }

    public FillViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mHeightList.clear();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            Integer heightInt = new Integer(h);
            mHeightList.add(heightInt);
        }
        Log.v("jiazaizhong", "widthMeasureSpec:" + heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
