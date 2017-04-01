package com.stone.recyclerview.c02_click;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.stone.recyclerview.R;
import com.stone.recyclerview.c03_abstract.base.BaseAdapter;
import com.stone.recyclerview.c03_abstract.base.BaseHolder;
import com.stone.recyclerview.c03_abstract.base.ItemClickSupport;
import com.stone.recyclerview.c05_decoration.RectDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * desc   : RecyclerView 添加 click事件
 * author : stone
 * email  : aa86799@163.com
 * time   : 29/03/2017 20 37
 */
public class MainActivity extends Activity {

    private List<String> mList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        initViews();

//        addItemClickWay1();
//        addItemClickWay2();
//        addItemClickWay3();
        addItemClickWay4();
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mList.add("" + (char) i);
        }
    }

    private void initViews() {
        mRecyclerView = new RecyclerView(this);
        setContentView(mRecyclerView);
        mRecyclerView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));

        //set layoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //add ItemDecoration
        mRecyclerView.addItemDecoration(new RectDecoration());

    }

    //基于RecyclerView#addOnItemTouchListener 添加touch监听
    private void addItemClickWay1() {
        final ClickAdapter1 adapter = new ClickAdapter1(this, mList);

        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(mRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        clickAnim(view);
                        System.out.println("onItemClick " + adapter.getItem(position));
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        System.out.println("onItemLongClick " + position);
                    }
                }));
    }

    /*
    adapter#bindCustomViewHolder 时，holder.itemView添加click监听
     */
    private void addItemClickWay2() {
        final ClickAdapter adapter = new ClickAdapter(this, mList);
        adapter.setListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                clickAnim(view);
                System.out.println("onItemClick " + adapter.getItem(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {
                System.out.println("onItemLongClick " + position);
            }
        });

        mRecyclerView.setAdapter(adapter);
    }

    /*
    在holder构造初始化时，添加click监听
     */
    private void addItemClickWay3() {
        final ClickAdapter3 adapter = new ClickAdapter3(this, mList);
        mRecyclerView.setAdapter(adapter);
    }

    /*
    基于RecyclerView.OnChildAttachStateChangeListener 添加监听
     */
    private void addItemClickWay4() {

        mRecyclerView.setItemAnimator(new SlideInLeftAnimator());

        final ClickAdapter1 adapter = new ClickAdapter1(this, mList);
        mRecyclerView.setAdapter(adapter);
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                System.out.println("onItem " + position + "  " + adapter.getItem(position));
            }
        }).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                System.out.println("onItemLongClick " + position);
                return true;
            }
        });

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //        mList.remove(3);
//                adapter.notifyItemRemoved(3); //无效
                adapter.removeItem(3);
            }
        }, 2000);

    }

    /**
     * 随机颜色
     */
    private static Random random = new Random();

    private static int getColor() {
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

    private static void clickAnim(final View view) {
        ValueAnimator anim = ValueAnimator.ofFloat(1, 1.2f, 1);
        anim.setDuration(300);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float rate = animation.getAnimatedFraction();
                float value = (float) animation.getAnimatedValue();
                view.setScaleX(value);
                view.setScaleY(value);
            }
        });
        anim.start();
    }

    private static class SimplifyVH extends BaseHolder {

        public SimplifyVH(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
        }

        public SimplifyVH(View view) {
            super(view);
        }

    }

    private static class ClickAdapter1 extends BaseAdapter<String, SimplifyVH> {

        public ClickAdapter1(Context context) {
            super(context);
        }

        public ClickAdapter1(Context context, List<String> list) {
            super(context, list);
        }

        @Override
        public int getCustomViewType(int position) {
            return 0;
        }

        @Override
        public SimplifyVH createCustomViewHolder(ViewGroup parent, int viewType) {
            return new SimplifyVH(
                    LayoutInflater.from(
                            parent.getContext()).inflate(R.layout.basic_simple, null, false));
        }

        @Override
        public void bindCustomViewHolder(SimplifyVH holder, final int position) {

            holder.itemView.setFocusable(true);//加了这句，电视上就能滚动了

            TextView tvTitle = (TextView) holder.itemView.findViewById(R.id.tv_title);
            tvTitle.setText(getItem(position));

            View vImg = holder.itemView.findViewById(R.id.v_img);
            vImg.setBackgroundColor(getColor());
        }
    }

    private static class ClickAdapter extends BaseAdapter<String, SimplifyVH> {

        private RecyclerItemClickListener.OnItemClickListener mListener;

        public ClickAdapter(Context context) {
            super(context);
        }

        public ClickAdapter(Context context, List<String> list) {
            super(context, list);
        }

        public void setListener(RecyclerItemClickListener.OnItemClickListener listener) {
            mListener = listener;
        }

        @Override
        public int getCustomViewType(int position) {
            return 0;
        }

        @Override
        public SimplifyVH createCustomViewHolder(ViewGroup parent, int viewType) {
            return new SimplifyVH(
                    LayoutInflater.from(
                            parent.getContext()).inflate(R.layout.basic_simple, null, false));
        }

        @Override
        public void bindCustomViewHolder(SimplifyVH holder, final int position) {

            holder.itemView.setFocusable(true);//加了这句，电视上就能滚动了

            TextView tvTitle = (TextView) holder.itemView.findViewById(R.id.tv_title);
            tvTitle.setText(getItem(position));

            View vImg = holder.itemView.findViewById(R.id.v_img);
            vImg.setBackgroundColor(getColor());

            final SimplifyVH vh = (SimplifyVH) holder;
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(v, position);
                    }
                }
            });
            vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mListener != null) {
                        mListener.onItemLongClick(v, position);
                    }
                    return true;
                }
            });
        }
    }

    private static class SimplifyVHWithListener extends BaseHolder {

        RecyclerItemClickListener.OnItemClickListener listener;

        public SimplifyVHWithListener(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
        }

        public SimplifyVHWithListener(View view, final RecyclerItemClickListener.OnItemClickListener listener) {
            super(view);
            this.listener = listener;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        listener.onItemLongClick(v, getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }

    private static class ClickAdapter3 extends BaseAdapter<String, SimplifyVHWithListener> {

        private RecyclerItemClickListener.OnItemClickListener mListener;

        public ClickAdapter3(Context context) {
            super(context);
        }

        public ClickAdapter3(Context context, List<String> list) {
            super(context, list);
        }

        public void setListener(RecyclerItemClickListener.OnItemClickListener listener) {
            mListener = listener;
        }

        @Override
        public int getCustomViewType(int position) {
            return 0;
        }

        @Override
        public SimplifyVHWithListener createCustomViewHolder(ViewGroup parent, int viewType) {
            return new SimplifyVHWithListener(
                    LayoutInflater.from(
                            parent.getContext()).inflate(R.layout.basic_simple, null, false),
                    new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            clickAnim(view);
                            System.out.println("onItemClick " + getItem(position));
                        }

                        @Override
                        public void onItemLongClick(View view, int position) {
                            System.out.println("onItemLongClick " + position + "__" + getItem(position));
                        }
                    });
        }

        @Override
        public void bindCustomViewHolder(SimplifyVHWithListener holder, final int position) {

            holder.itemView.setFocusable(true);//加了这句，电视上就能滚动了

            TextView tvTitle = (TextView) holder.itemView.findViewById(R.id.tv_title);
            tvTitle.setText(getItem(position));

            View vImg = holder.itemView.findViewById(R.id.v_img);
            vImg.setBackgroundColor(getColor());
        }
    }
}
