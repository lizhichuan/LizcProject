package com.njcool.lzccommon.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用ListViewAdapter
 * Created by lizhichuan on 15/12/23.
 */
public abstract class CoolCommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;
    private HolderClickListener mHolderClickListener;

    public CoolCommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas == null ? new ArrayList<T>() : mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    public CoolCommonAdapter(List<T> mDatas, Context context, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas == null ? new ArrayList<T>() : mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    public List<T> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        if (mDatas != null) {
            return mDatas.size();
        } else {
            return 0;
        }
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CoolCommonViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        onBindView(viewHolder, getItem(position));
        return viewHolder.getConvertView();

    }

    //    public abstract void convert(CoolCommonViewHolder helper, T item);
    public abstract void onBindView(CoolCommonViewHolder holder, T item);

    private CoolCommonViewHolder getViewHolder(int position, View convertView,
                                               ViewGroup parent) {
        return CoolCommonViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }


    public void addAll(List<T> elem) {
        if (mDatas != null) {
            mDatas.clear();
        }
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        mDatas.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        mDatas.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        mDatas.clear();
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }

    public void SetOnSetHolderClickListener(HolderClickListener holderClickListener) {
        this.mHolderClickListener = holderClickListener;
    }

    public interface HolderClickListener {
        public void onHolderClick(Drawable drawable, int[] start_location);
    }
}
