package com.stone.recyclerview.c01_simple;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.stone.recyclerview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * desc   :
 * author : stone
 * email  : aa86799@163.com
 * time   : 29/03/2017 20 37
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        basicSimple();
    }

    private void basicSimple() {
        RecyclerView rv = new RecyclerView(this);
        setContentView(rv);
        rv.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));

        final List<String> list = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            list.add("" + (char) i);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        rv.setAdapter(new RecyclerView.Adapter<BasicSimpleVH>() {
            @Override//创建ViewHolder
            public BasicSimpleVH onCreateViewHolder(ViewGroup parent, int viewType) {
                return new BasicSimpleVH(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.basic_simple, null, false));

            }

            @Override//当绑定ViewHolder
            public void onBindViewHolder(BasicSimpleVH holder, int position) {
                holder.itemView.setFocusable(true);//加了这句，电视上就能滚动了


                TextView tvTitle = (TextView) holder.itemView.findViewById(R.id.tv_title);
                tvTitle.setText(list.get(position));

                View vImg = holder.itemView.findViewById(R.id.v_img);
                vImg.setBackgroundColor(getColor());
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        });
    }

    private static class BasicSimpleVH extends RecyclerView.ViewHolder {

        public BasicSimpleVH(View itemView) {
            super(itemView);
        }
    }


    /**
     * 随机颜色
     */
    Random random = new Random();
    private int getColor() {
        StringBuilder sb = new StringBuilder();
        String temp;
        for (int i = 0; i < 4; i++) {
            temp = Integer.toHexString(random.nextInt(0xFF));
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            sb.append(temp);
        }
        return Color.parseColor("#" + sb.toString());
    }
}
