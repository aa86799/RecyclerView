package com.stone.recyclerview.useful_method;

import android.support.v7.widget.RecyclerView;

/**
 * desc   :
 * author : stone
 * email  : aa86799@163.com
 * time   : 08/04/2017 13 39
 */
public class Useful {

    private void test(RecyclerView recyclerView, RecyclerView anotherRecyclerView) {
        /*
        RecyclerView can perform several optimizations if it can know in advance that RecyclerView's
     * size is not affected by the adapter contents. RecyclerView can still change its size based
     * on other factors (e.g. its parent's size) but this size calculation cannot depend on the
     * size of its children or contents of its adapter (except the number of items in the adapter).
     * <p>
     * If your use of RecyclerView falls into this category, set this to {@code true}. It will allow
     * RecyclerView to avoid invalidating the whole layout when its adapter contents change.
     *
     * @param hasFixedSize true if adapter changes cannot affect the size of the RecyclerView.
     *
        RecyclerView 可以执行几个优化，如果预先知道RV的尺寸(size)不受adapter内容影响。
        RecyclerView 基于其它因素仍能改变其尺寸，但其尺寸的计算不能依赖 其children的尺寸 或 adapter的内容(除了adapter中item的数量)。
        如果在使用RecyclerView时，用了瀑布流，且设置为true；那么将会在adapter内容改变时，避免invalidating整个layout

        true : adapter的改变 不能影响RecyclerView的尺寸
         */
        recyclerView.setHasFixedSize(true);

        recyclerView.setRecycledViewPool(anotherRecyclerView.getRecycledViewPool());

    }
}
