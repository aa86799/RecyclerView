package com.stone.recyclerview.c05_decoration;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.stone.recyclerview.R;
import com.stone.recyclerview.c02_click.RecyclerItemClickListener;
import com.stone.recyclerview.c03_abstract.base.BaseAdapter;
import com.stone.recyclerview.c03_abstract.base.BaseHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * desc   : RecyclerView 添加 click事件
 * author : stone
 * email  : aa86799@163.com
 * time   : 29/03/2017 20 37
 */
public class MainActivity extends Activity {

    private List<String> mList;
    private RecyclerView mRecyclerView;
    private static boolean mUseGrid = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        initViews();

        addItemClickWay3();
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

        if (mUseGrid) {
            //默认纵向；spanCount纵向表示列，横向表示行
            GridLayoutManager layoutManager = new GridLayoutManager(this, 5);
//            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(layoutManager);

//            mRecyclerView.addItemDecoration(new GridDecoration(Color.parseColor("#330000ff")));
            mRecyclerView.addItemDecoration(new GridDividerItemDecoration(12, Color.RED));

        } else {
            //set layoutManager
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);

            //add ItemDecoration
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
//        mRecyclerView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this,
//                android.support.v7.widget.DividerItemDecoration.VERTICAL));
            mRecyclerView.addItemDecoration(new RectDecoration(Color.parseColor("#33FF0000")));
        }


    }

    /*
    在holder构造初始化时，添加click监听
     */
    private void addItemClickWay3() {
        final ClickAdapter3 adapter = new ClickAdapter3(this, mList);

//        ImageView imageView = new ImageView(this);
//        imageView.setLayoutParams(new RecyclerView.LayoutParams(-1, 600));
//        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        imageView.setImageResource(R.drawable.cat);
//        adapter.addHeaderView(imageView);

        adapter.setListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                clickAnim(view);
                System.out.println("onItemClick " + position + adapter.getItem(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {
                System.out.println("onItemLongClick " + position + "__" + adapter.getItem(position));
            }
        });
        mRecyclerView.setAdapter(adapter);
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

    private static class SimplifyVHWithListener extends BaseHolder {

        public SimplifyVHWithListener(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
        }

        public SimplifyVHWithListener(View view, final RecyclerItemClickListener.OnItemClickListener listener) {
            super(view);
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

    private static class ClickAdapter3 extends BaseAdapter<String, BaseHolder> {

        public static final int VIEW_TYPE_TEXT = 1;
        public static final int VIEW_TYPE_IMAGE = 2;
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
            if (position % 2 == 0) {
                return VIEW_TYPE_TEXT;
            } else {
                return VIEW_TYPE_IMAGE;
            }
        }

        @Override
        public BaseHolder createCustomViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case VIEW_TYPE_TEXT:
                    if (mUseGrid) {
                        return new SimplifyVHWithListener(
                                LayoutInflater.from(parent.getContext()).inflate(R.layout.decor_simple, null, false),
                                mListener
                        );
                    } else {
                        return new SimplifyVHWithListener(
                                LayoutInflater.from(parent.getContext()).inflate(R.layout.decor_simple, null, false),
                                mListener
                        );
                    }

                case VIEW_TYPE_IMAGE:
                    return new ImageViewHolder(
                            LayoutInflater.from(parent.getContext()).inflate(R.layout.image_holder, null, false),
                            mListener);
            }
            return null;
        }

        @Override
        public void bindCustomViewHolder(BaseHolder holder, final int position) {
            holder.itemView.setFocusable(true);//加了这句，电视上就能滚动了

            switch (getItemViewType(position)) {
                case VIEW_TYPE_TEXT: {
                    TextView tvTitle = holder.getView(R.id.tv_title);
                    tvTitle.setText(getItem(position));

                    View vImg = holder.getView(R.id.v_img);
//                    vImg.setBackgroundColor(getColor());
                    vImg.setBackgroundColor(Color.parseColor("#c00378ae"));
                }
                    break;

                case VIEW_TYPE_IMAGE: {
                    TextView tvTitle = holder.getView(R.id.tv_title);
                    tvTitle.setText(getItem(position));

                    ImageView imageView = holder.getView(R.id.iv_image);
                    imageView.setImageResource(R.drawable.scenery);
                }
                    break;
            }

        }
    }

    private static class ImageViewHolder extends BaseHolder {

        public ImageViewHolder(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
        }

        public ImageViewHolder(View view, final RecyclerItemClickListener.OnItemClickListener listener) {
            super(view);
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
}