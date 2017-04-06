package com.stone.recyclerview.c03_abstract;


/*
RecyclerView 相关的抽象基类

BaseHolder
    使ViewHolder只用来缓存View。
    添加SparseArray，使之来缓存View。
    添加BaseHolder(View view)构造器，外部更方便控制View。
    保留getContext()方法，方便获取Context对象。
    getView(resid)，简化itemView.findviewById()

Adapter
    Adapter拆分为两个抽象类：AbsAdapter与BaseAdapter，其中：
    AbsAdapter：封装了和ViewHolder和HeaderView，FooterView相关的方法。
    BaseAdapter：继承AbsAdapter，封装了数据相关的方法。
各自聚焦于不同的方面，方面日后扩展。


 */
