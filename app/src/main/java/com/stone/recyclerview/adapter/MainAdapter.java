package com.stone.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.stone.recyclerview.R;

import java.util.List;

/**
 * author : stone
 * email  : aa86799@163.com
 * time   : 16/4/7 17 24
 */
public class MainAdapter extends BaseRecyclerViewAdapter<String> {

    public MainAdapter(Context context, int layoutID, List<String> datas) {
        super(context, layoutID, datas);
    }

    @Override
    public void onBindViewHolderSetData(@NonNull final BaseRecyclerViewHolder holder, final int position) {
        String str = getItem(position);
        holder.setText(R.id.tv_text, str);

//        holder.getLayoutPosition(); //layout中位置
//        holder.getAdapterPosition(); //adapter中位置

    }

    public void addData(String data, int index) {
        super.mDatas.add(index, data);
//        notifyDataSetChanged(); //使用它会瞬间更新，没有动画效果
        notifyItemInserted(index);

    }

    public void deleteData(String data) {
        int index = super.mDatas.indexOf(data);
        super.mDatas.remove(data);
//        notifyDataSetChanged(); //使用它会瞬间更新，没有动画效果
        notifyItemRemoved(index);
    }
}
