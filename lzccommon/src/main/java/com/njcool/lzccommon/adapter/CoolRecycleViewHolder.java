package com.njcool.lzccommon.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lizhichuan on 15/12/23.
 */
public class CoolRecycleViewHolder extends RecyclerView.ViewHolder {

    protected final SparseArray<View> mViews;
    protected View mConvertView;
    private Context mContext;

    public CoolRecycleViewHolder(Context context, View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
        mContext = context;
        mConvertView = itemView;
    }

    public static CoolRecycleViewHolder createViewHolder(Context context, View itemView) {
        CoolRecycleViewHolder holder = new CoolRecycleViewHolder(context, itemView);
        return holder;
    }

    public static CoolRecycleViewHolder createViewHolder(Context context,
                                                         ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        CoolRecycleViewHolder holder = new CoolRecycleViewHolder(context, itemView);
        return holder;
    }

    /**
     * 通过控件的Id获取对应的控件，如果没有则加入mViews，则从item根控件中查找并保存到mViews中
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getmConvertView() {
        return mConvertView;
    }


}

