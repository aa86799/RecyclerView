package com.stone.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.stone.recyclerview.adapter.MainAdapter;
import com.stone.recyclerview.decoration.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * 下拉刷新
 * SwipeRefreshLayout
 * author : stone
 * email  : aa86799@163.com
 * time   : 16/5/7 17 24
 */
public class SwipeActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private RecyclerView.Adapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.swipe_recyclerview);

        initDatas();

        initViews();

        initListeners();
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            mDatas.add("四川" + i);
        }
    }

    private void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sr_layout);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_view);
        mAdapter = new MainAdapter(this, R.layout.item_adapter, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.setItemAnimator(new SlideInLeftAnimator());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void initListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                for (int i = 0; i < 5; i++) {
                    ((MainAdapter) mAdapter).addData("江苏" + i, 0);
                }
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.notifyDataSetChanged();//每有这个 RV不刷新
            }
        });

    }

}
