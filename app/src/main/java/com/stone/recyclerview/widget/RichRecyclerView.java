package com.stone.recyclerview.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 实现了滑动到底部的处理，暂未添加自定义监听器
 * 实现了当LayoutManger是StaggeredGridLayoutManager时，滑到底部的那一行上的子view高度占满RecyclerView
 * <p/>
 * author : stone
 * email  : aa86799@163.com
 * time   : 16/5/6 14 23
 */
public class RichRecyclerView extends RecyclerView {

    private OnScrollToBottomListener mOnScrollToBottomListener;

    public RichRecyclerView(Context context) {
        this(context, null);
    }

    public RichRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RichRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public interface OnScrollToBottomListener {
        void onScrollToBottom(int position);
        void onScrollToBottom(int[] positions);
    }

    public void setOnScrollToBottomListener(OnScrollToBottomListener onScrollToBottomListener) {
        this.mOnScrollToBottomListener = onScrollToBottomListener;
    }

    @Override
    public void onScrollStateChanged(int state) {
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            LayoutManager layoutManager = getLayoutManager();

            if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager lm = (StaggeredGridLayoutManager) layoutManager;
                int columnCount = lm.getColumnCountForAccessibility(null, null);
                int positions[] = new int[columnCount];
                lm.findLastVisibleItemPositions(positions);
                boolean flag = false;
                for (int i = 0; i < positions.length; i++) {
                    /**
                     * 判断postions[i] 是否在最后一行
                     */
                    if (positions[i] >= lm.getItemCount() - columnCount) {
                        ViewGroup.LayoutParams layoutParams = lm.findViewByPosition(positions[i]).getLayoutParams();
                        layoutParams.height = lm.findViewByPosition(positions[i]).getHeight() +
                                getHeight() - lm.findViewByPosition(positions[i]).getBottom();
                        lm.findViewByPosition(positions[i]).setLayoutParams(layoutParams);
                        flag = true;
                    }
                }
                if (flag) {
                    if (mOnScrollToBottomListener != null) {
                        mOnScrollToBottomListener.onScrollToBottom(positions);
                    }
                }

            } else if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager lm = (LinearLayoutManager) layoutManager;
                int position = lm.findLastVisibleItemPosition();
                if (position + 1 == lm.getItemCount()) {
                    if (mOnScrollToBottomListener != null) {
                        mOnScrollToBottomListener.onScrollToBottom(position);
                    }
                }
            }
        }
        super.onScrollStateChanged(state);
    }
}
