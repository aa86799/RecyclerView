package com.stone.recyclerview.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.ImageView;  
import android.widget.TextView;  
  
  
/** 
 * author : stone 
 * email  : aa86799@163.com 
 * time   : 15/10/11 14 38 
 */  
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener{
  
    private View mItemView;  
    private SparseArray<View> mViews;  //管理listView-item中的view
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        boolean onItemLongClick(View view, int position);
    }

    private BaseRecyclerViewHolder(@NonNull View itemView,
                                   @Nullable OnItemClickListener onItemClickListener) {
        super(itemView);
//        this.mItemView.setTag(this);
        this.mItemView = itemView;
        this.mViews = new SparseArray<View>();
        this.onItemClickListener = onItemClickListener;

        this.mItemView.setOnClickListener(this);
        this.mItemView.setOnLongClickListener(this);
    }
  
    private BaseRecyclerViewHolder(Context context, int layoutId, ViewGroup parent,
                                   @Nullable OnItemClickListener onItemClickListener) {
        this(LayoutInflater.from(context).inflate(layoutId, parent), onItemClickListener);
    }  
  
    public static BaseRecyclerViewHolder getInstance(Context context, int layoutId, ViewGroup parent,
                                                     @Nullable OnItemClickListener onItemClickListener) {
        return new BaseRecyclerViewHolder(context, layoutId, parent, onItemClickListener);
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null){
            onItemClickListener.onItemClick(itemView, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (onItemClickListener != null){
            return onItemClickListener.onItemLongClick(itemView, getAdapterPosition());
        }
        return false;
    }

    public <T extends View> T getView(int viewId) {  
        View view = mViews.get(viewId);  
        if (view == null) {  
            view = mItemView.findViewById(viewId);  
            mViews.put(viewId, view);  
        }  
        return (T) view;  
    }  
  
    public <T> void setTag(int viewId, T tag) {  
        getView(viewId).setTag(tag);  
    }  
  
    public <T> T getTag(int viewId) {  
        return (T) getView(viewId).getTag();  
    }  
  
    /*------------------------  设置view属性(以后扩展) --------------------------------*/  
  
    public BaseRecyclerViewHolder setText(int viewId, String text) {
        ((TextView) getView(viewId)).setText(text);  
        return this;  
    }  
  
    public BaseRecyclerViewHolder setText(int viewId, int resId) {//R.string.
        ((TextView) getView(viewId)).setText(resId);  
        return this;  
    }  
  
    public BaseRecyclerViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ((ImageView) getView(viewId)).setImageBitmap(bitmap);  
        return this;  
    }  
  
    public BaseRecyclerViewHolder setImageResource(int viewId, int resId) {
        ((ImageView) getView(viewId)).setImageResource(resId);  
        return this;  
    }  
  
}