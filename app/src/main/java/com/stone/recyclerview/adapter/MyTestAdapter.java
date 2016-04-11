package com.stone.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stone.recyclerview.R;

import java.util.List;

/**
 * 普通写法
 * author : stone
 * email  : aa86799@163.com
 * time   : 16/4/8 16 37
 */
public class MyTestAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<String> mDatas;
    private LayoutInflater mInflater;

    public MyTestAdapter(Context context, List<String> datas) {
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_adapter, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


}
class MyViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    public MyViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv_text);
    }
}