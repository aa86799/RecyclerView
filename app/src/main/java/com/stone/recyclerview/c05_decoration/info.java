package com.stone.recyclerview.c05_decoration;


/*
RecyclerView 添加 ItemDecoration

 主要重写 ItemDecoration的 onDraw()、onDrawOver()、getItemOffsets()方法
    onDraw          ---     它在item之前绘制，就像装饰了一个item的背景，在item之前绘制
    onDrawOver      ---     它在item之后绘制，就像装饰了一个item的前景，在item之后绘制
    getItemOffsets  ---     指定item间的偏移量。由指定outRect的left、top、right、bottom的像素值，
                                来偏移item。指定了偏移量后，可能会影响item的宽或高
 */
