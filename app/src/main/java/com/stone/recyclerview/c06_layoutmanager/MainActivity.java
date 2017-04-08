package com.stone.recyclerview.c06_layoutmanager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stone.recyclerview.R;
import com.stone.recyclerview.c03_abstract.base.BaseAdapter;
import com.stone.recyclerview.c03_abstract.base.BaseHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * desc   :
 * author : stone
 * email  : aa86799@163.com
 * time   : 08/04/2017 10 58
 */
public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<String> mList;
    private int mOrientation = LinearLayoutManager.HORIZONTAL;
    private boolean mIsStaggered;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        initViews();
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            mList.add("" + (char) i);
        }
    }

    private void initViews() {
        mRecyclerView = new RecyclerView(this);
        setContentView(mRecyclerView);
        mRecyclerView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));

        mRecyclerView.setHasFixedSize(true);

        setAdapter();

//        useLinearLayoutManager();
//        useGridLayoutManager();
        useStaggeredGridLayoutManager();
    }

    private void setAdapter() {
        mRecyclerView.setAdapter(new BaseAdapter<String, BaseHolder>(this, mList) {
            @Override
            public int getCustomViewType(int position) {
                return 0;
            }

            @Override
            public BaseHolder createCustomViewHolder(ViewGroup parent, int viewType) {
                if (mOrientation == LinearLayoutManager.HORIZONTAL) {
                    return new BaseHolder(parent.getContext(), R.layout.lm_horizontal);
                } else {
                    return new BaseHolder(parent.getContext(), R.layout.basic_simple);
                }
            }

            @Override
            public void bindCustomViewHolder(BaseHolder holder, int position) {
                holder.itemView.setFocusable(true);//加了这句，电视上就能滚动了

                TextView tvTitle = holder.getView(R.id.tv_title);
                tvTitle.setText(getItem(position));

                View vImg = holder.getView(R.id.v_img);
                vImg.setBackgroundColor(getColor());

                if (mIsStaggered) {
                    float size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
                    int w = mOrientation == LinearLayoutManager.HORIZONTAL ? (int)size : -1;
                    int h = mOrientation == LinearLayoutManager.HORIZONTAL ? -1 : (int)size;
                    if (mOrientation == LinearLayoutManager.HORIZONTAL) {
                        //每个w都可能不同
//                        w = (int) (size + Math.random() * size);

                        //固定每个position上的item 的 w
                        w = (int)size;
                        if (position % 2 == 1) {
                            w = w / 2;
                        }
                    } else {
                        h = (int) (size + Math.random() * size);
                    }
//                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(w, h));
                    vImg.setLayoutParams(new RelativeLayout.LayoutParams(w, h));
                }
            }
        });
    }

    private void useLinearLayoutManager() {//线性 横向/纵向
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //默认线性纵向
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, mOrientation, true); //默认线性纵向

        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void useGridLayoutManager() {//网格式
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, mOrientation, false);

        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void useStaggeredGridLayoutManager() {//交错网格式
        mIsStaggered = true;

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, mOrientation);
        layoutManager.setAutoMeasureEnabled(false);
        layoutManager.setItemPrefetchEnabled(false);
        mRecyclerView.setLayoutManager(layoutManager);

    }


    private static Random random = new Random();
    private static int getColor() {
        StringBuilder sb = new StringBuilder();
        String temp;
        for (int i = 0; i < 4; i++) {
            temp = Integer.toHexString(i == 0 ? random.nextInt(0xc8) : random.nextInt(0xFF));
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            sb.append(temp);
        }
        return Color.parseColor("#" + sb.toString());
    }
}
