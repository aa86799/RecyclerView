package com.stone.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.stone.recyclerview.adapter.StaggeredAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author : stone
 * email  : aa86799@163.com
 * time   : 16/4/9 16 16
 */
public class StaggeredActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acti_main);

        initDatas();

        initViews();
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            mDatas.add("四川" + i);
        }
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_view);
//        mAdapter = new StaggeredAdapter(this, R.layout.item_adapter, mDatas, StaggeredGridLayoutManager.HORIZONTAL);
        mAdapter = new StaggeredAdapter(this, R.layout.item_adapter, mDatas, StaggeredGridLayoutManager.VERTICAL);
//        mAdapter = new MyTestAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        //横向时 该动态holder.itemview 的宽度
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));

        //纵向时 该动态holder.itemview 的高度
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
    }
}
