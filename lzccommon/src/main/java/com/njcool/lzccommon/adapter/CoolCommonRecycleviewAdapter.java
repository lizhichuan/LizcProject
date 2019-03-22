package com.njcool.lzccommon.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用RecycleAdapter
 * Created by lizhichuan on 15/12/23.
 */
public abstract class CoolCommonRecycleviewAdapter<T> extends RecyclerView.Adapter<CoolRecycleViewHolder> {
    protected List<T> mLists;
    protected Context mContext;
    protected int layoutID;
    private HolderClickListener mHolderClickListener;

    public void setOnItemClickListener(CoolOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private CoolOnItemClickListener onItemClickListener;

    public CoolCommonRecycleviewAdapter(List<T> mLists, Context mContext, int layoutID) {
        this.mLists = mLists == null ? new ArrayList<T>() : mLists;
        this.mContext = mContext;
        this.layoutID = layoutID;
    }

    public CoolCommonRecycleviewAdapter(Context mContext, List<T> mLists, int layoutID) {
        this.mLists = mLists == null ? new ArrayList<T>() : mLists;
        this.mContext = mContext;
        this.layoutID = layoutID;
    }

    public void setmLists(List<T> mLists) {
        this.mLists = mLists;
    }

    public void setmDatas(List<T> mLists) {
        this.mLists = mLists;
    }

    public List<T> getmLists() {
        return mLists;
    }

    @Override
    public CoolRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CoolRecycleViewHolder holder = new CoolRecycleViewHolder(mContext, LayoutInflater.from(mContext).inflate(layoutID, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final CoolRecycleViewHolder holder, final int position) {
        if (onItemClickListener != null) {
            holder.getmConvertView().setClickable(true);
            holder.getmConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });
        }
        onBindView(holder, position);
    }

    protected abstract void onBindView(CoolRecycleViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mLists == null ? 0 : mLists.size();
    }


    public void addData(int position, T t) {
        mLists.add(position, t);
        notifyItemInserted(position);
        // 加入如下代码保证position的位置正确性
        if (position != mLists.size() - 1) {
            notifyItemRangeChanged(position, mLists.size() - position);

        }
    }

    public void removeData(int position) {
        mLists.remove(position);
        notifyItemRemoved(position);
        if (position != mLists.size() - 1) {
            notifyItemRangeChanged(position, mLists.size() - position);
        }
    }

    public void SetOnSetHolderClickListener(HolderClickListener holderClickListener) {
        this.mHolderClickListener = holderClickListener;
    }

    public interface HolderClickListener {
        public void onHolderClick(Drawable drawable, int[] start_location);
    }

}
