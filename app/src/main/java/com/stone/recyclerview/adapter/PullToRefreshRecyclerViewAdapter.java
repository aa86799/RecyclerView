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
public abstract class PullToRefreshRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected List<T> mDatas;
    protected Context mContext;
    private int mLayoutID;
    private BaseRecyclerViewHolder.OnItemClickListener mOnItemClickListener;

   /* private final int TYPE_NORMAL = 0;
    private final int TYPE_HEADER = 1; //header 下拉刷新
    private final int TYPE_FOOTER = 2; //footer 上拉加载更多
    private final int TYPE_LIST = 3;  //LinearLayoutManager
    private final int TYPE_STAGGER = 4; //StaggeredLayoutManager
    private boolean mIsFooterEnable; //是否启用上拉加载
    private boolean mIsHeaderEnable; //是否启用上拉刷新
    private int mHeaderResid;
    private int mFooterResid;*/




    public PullToRefreshRecyclerViewAdapter(Context context, int layoutID, List<T> datas) {
        this.mContext = context;
        this.mLayoutID = layoutID;
        this.mDatas = datas == null ? new ArrayList<T>() : datas;
    }

    public void setOnItemClickListener(BaseRecyclerViewHolder.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
    /*    if (position == 0 && mIsHeaderEnable && mHeaderResid > 0) {
            return TYPE_HEADER;
        } else if (position == getItemCount() - 1 && mIsFooterEnable && mFooterResid > 0) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }*/

        return super.getItemViewType(position);
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