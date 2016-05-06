package com.stone.recyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.stone.recyclerview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author : stone
 * email  : aa86799@163.com
 * time   : 16/4/9 16 22
 */
public class StaggeredAdapter extends BaseRecyclerViewAdapter<String> {

    private List<Integer> mWHs; //宽或高
    private int mOrientation;

    public StaggeredAdapter(Context context, int layoutID, List<String> datas, int orientation) {
        super(context, layoutID, datas);
        this.mWHs = new ArrayList<>();
        this.mOrientation = orientation;

        for (int i = 0, len = datas.size(); i < len; i++) {
            if (mOrientation == StaggeredGridLayoutManager.VERTICAL) {
                mWHs.add((int) (100 + Math.random() * 100));
            } else {
                mWHs.add((int) (80 + Math.random() * 100));
            }
        }

    }

    @Override
    public void onBindViewHolderSetData(@NonNull BaseRecyclerViewHolder holder, int position) {

        holder.itemView.setBackgroundColor(getColor());

        String str = getItem(position);
        holder.setText(R.id.tv_text, str);

        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(-2, -2);
        }
        if (mOrientation == StaggeredGridLayoutManager.VERTICAL) {
//            holder.itemView.setMinimumHeight(mWHs.get(position));//该方法改变itemview高度，并requestLayout()，子view没有重新请求layout
            params.height = mWHs.get(position);
        } else {
//            holder.itemView.setMinimumWidth(mWHs.get(position));
            params.width = mWHs.get(position);
        }
        holder.itemView.setLayoutParams(params);


    }


    /**
     * 随机颜色
     *
     * @return
     */
    @CheckResult
    private int getColor() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String temp;
        for (int i = 0; i < 3; i++) {
            temp = Integer.toHexString(random.nextInt(0xFF));
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            sb.append(temp);
        }
        return Color.parseColor("#" + sb.toString());
    }
}
