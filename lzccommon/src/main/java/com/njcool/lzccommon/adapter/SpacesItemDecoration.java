package com.njcool.lzccommon.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by chuan on 2017/7/6.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int cloums;

    public SpacesItemDecoration(int space, int cloums) {
        this.space = space;
        this.cloums = cloums;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = space;
        outRect.bottom = space;
        if (parent.getChildLayoutPosition(view) % cloums == 0) {
            outRect.left = 0;
        }


    }

}
