package com.stone.recyclerview.c02_click;


/*
RecyclerView 添加 click事件

1. 基于RecyclerView#addOnItemTouchListener 添加touch监听

2. 对holder.itemView添加click监听

    a. 在adapter#bindCustomViewHolder 时，holder.itemView添加click监听

    b. 在holder构造初始化时，添加click监听

3. 基于RecyclerView.OnChildAttachStateChangeListener 添加监听

 */