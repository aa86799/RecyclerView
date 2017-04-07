package com.stone.recyclerview.c05_decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.stone.recyclerview.R;

import static android.R.attr.offset;

/**
 * author : stone
 * email  : aa86799@163.com
 * time   : 17/4/7 13 32
 */
public class GridDecoration extends RecyclerView.ItemDecoration {

   private int mBackColor;

    public GridDecoration(int color) {
        super();
        this.mBackColor = color;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);


        Paint paint = new Paint();
        paint.setColor(mBackColor);
//
//        for (int i = 0, len = parent.getLayoutManager().getChildCount(); i < len; i++) {
//            final View child = parent.getChildAt(i);
//
//            int offset = 5;
//            float left = child.getLeft() - offset;
//            float top = child.getTop() - offset;
//            float right = child.getRight() + offset;
//            float bottom = child.getBottom() + offset;
//
//            c.drawRect(left, top, right, bottom, paint);
////            System.out.println("onItem" + ",," + child.getLeft() + ",," + child.getTop() + ",," + child.getRight() + ",," + child.getBottom());
////            System.out.println("onItem" + ",," + left + ",," + top + ",," + right + ",," + bottom);
//
//            c.drawRect(left, top, right, bottom, paint);
//        }

        for (int i = 0, len = parent.getLayoutManager().getChildCount(); i < len; i++) {

        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

        Paint paint = new Paint();
        paint.setColor(mBackColor);

        for (int i = 0, len = parent.getLayoutManager().getChildCount(); i < len; i++) {
            final View child = parent.getChildAt(i);

            int offset = 5;
            float left = child.getLeft() - offset;
            float top = child.getTop() - offset;
            float right = child.getRight() + offset;
            float bottom = child.getBottom() + offset;

            c.drawRect(left, top, right, bottom, paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.offset(20, 30);
//        System.out.println(outRect.left);
//        System.out.println(outRect.top);
//        System.out.println(outRect.right);
//        System.out.println(outRect.bottom);

        int position = parent.getChildAdapterPosition(view);
        if (position % 2 == 1) {
            outRect.offsetTo(100, 20);
//            outRect.set(l, t, r, b);

            System.out.println(state.get(R.id.iv_image));
            System.out.println(state.didStructureChange());
            System.out.println(state.getItemCount());
            System.out.println(state.getTargetScrollPosition());
            System.out.println(state.hasTargetScrollPosition());
            System.out.println(state.isMeasuring());
            System.out.println(state.isPreLayout());
            System.out.println(state.willRunPredictiveAnimations());
            System.out.println(state.willRunSimpleAnimations());
//            state.put(R.id, data);
//            state.remove(R.id);
        }
    }
}
