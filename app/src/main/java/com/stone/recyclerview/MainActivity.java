package com.stone.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.stone.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.stone.recyclerview.adapter.BaseRecyclerViewHolder;
import com.stone.recyclerview.adapter.MainAdapter;
import com.stone.recyclerview.decoration.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * author : stone
 * email  : aa86799@163.com
 * time   : 16/4/7 16 42
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private BaseRecyclerViewAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acti_main);

        initDatas();

        initViews();

    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add("中华人民共和国\n美利坚合众国\n南极洲" + i);
        }
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_view);
        mAdapter = new MainAdapter(this, R.layout.item_adapter, mDatas);
//        mAdapter = new MyTestAdapter(this, mDatas);

//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setAdapter(new AlphaInAnimationAdapter(mAdapter));
//        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(mAdapter));
        mRecyclerView.setAdapter(new SlideInBottomAnimationAdapter(mAdapter));
//        mRecyclerView.setAdapter(new SlideInRightAnimationAdapter(mAdapter));


        mRecyclerView.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(1f)));
        mRecyclerView.getItemAnimator().setAddDuration(500);
        mRecyclerView.getItemAnimator().setChangeDuration(500);
        mRecyclerView.getItemAnimator().setMoveDuration(200);
        mRecyclerView.getItemAnimator().setRemoveDuration(500);

        mAdapter.setOnItemClickListener(new BaseRecyclerViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getBaseContext(), position + " click: " + mDatas.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, int position) {
                Toast.makeText(getBaseContext(), position + " long click:" + mDatas.get(position), Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                break;
            case R.id.action_gridview:
                //GridLayoutManager 继承自 LinearLayoutManager
                //横向，3行    true:数据反转，水平时会从右向左
//                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.HORIZONTAL, true));
                //纵向，3列   跟普通的GridView表现一样
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
                break;
            case R.id.action_hor_gridView:
                //横向时 数据会错列
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
                //纵向时跟普通的GridView表现一样
//                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                break;
            case R.id.action_staggered:
                //当item的宽高不一样时，再使用StaggeredGridLayoutManager 就会有瀑布流的效果
                startActivity(new Intent(this, StaggeredActivity.class));
                break;

            case R.id.action_add:
                if (mAdapter instanceof MainAdapter) {
                    ((MainAdapter) mAdapter).addData("大雪", 2);
                }
                break;
            case R.id.action_del:
                if (mAdapter instanceof MainAdapter) {
                    ((MainAdapter) mAdapter).deleteData("大雪");
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
