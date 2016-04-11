package com.stone.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * author : stone
 * email  : aa86799@163.com
 * time   : 15/10/11 14 22
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected List<T> mDatas;
    protected Context mContext;
    private int mLayoutID;
    private BaseRecyclerViewHolder.OnItemClickListener mOnItemClickListener;

    public BaseRecyclerViewAdapter(Context context, int layoutID, List<T> datas) {
        this.mContext = context;
        this.mLayoutID = layoutID;
        this.mDatas = datas == null ? new ArrayList<T>() : datas;
    }

    public void setOnItemClickListener(BaseRecyclerViewHolder.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            //根据viewType 创建不同的ViewHolder
            default:
                break;
        }
        return BaseRecyclerViewHolder.getInstance(mContext, mLayoutID, null, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(final BaseRecyclerViewHolder holder, final int position) {
        onBindViewHolderSetData(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public T getItem(int position) {
        return (T) mDatas.get(position);
    }

    public abstract void onBindViewHolderSetData(@NonNull BaseRecyclerViewHolder holder, int position);
}