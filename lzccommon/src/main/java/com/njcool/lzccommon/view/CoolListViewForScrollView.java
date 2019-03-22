package com.njcool.lzccommon.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Cool listview与ScrollView共存
 * Created by lizhichuan on 16/1/12.
 */
public class CoolListViewForScrollView extends ListView {
    public CoolListViewForScrollView(Context context) {
        super(context);
    }

    public CoolListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoolListViewForScrollView(Context context, AttributeSet attrs,
                                     int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
