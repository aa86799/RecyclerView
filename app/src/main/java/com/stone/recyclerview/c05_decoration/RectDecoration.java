package com.stone.recyclerview.c05_decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author : stone
 * email  : aa86799@163.com
 * time   : 16/4/8 18 21
 */
public class RectDecoration extends RecyclerView.ItemDecoration {

    public RectDecoration() {
        super();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#33FF0000"));

        for (int i = 0; i < parent.getLayoutManager().getChildCount(); i++) {
            final View child = parent.getChildAt(i);

            int offset = 5;
            float left = child.getLeft() - offset;
            float top = child.getTop() + offset * 4;
            float right = child.getRight() + offset;
            float bottom = child.getBottom() + offset;
//            System.out.println("onItem" + ",," + child.getLeft() + ",," + child.getTop() + ",," + child.getRight() + ",," + child.getBottom());
//            System.out.println("onItem" + ",," + left + ",," + top + ",," + right + ",," + bottom);

            c.drawRect(left, top, right, bottom, paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.offset(20, 0);
//        System.out.println(outRect.left);
//        System.out.println(outRect.top);
//        System.out.println(outRect.right);
//        System.out.println(outRect.bottom);
    }
}
