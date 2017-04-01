package com.stone.recyclerview.c01_simple;


/*
简单使用 RecyclerView。 简称RV
RV，只管回收复用。

布局交由LayoutManager，对应set方法 rv.setLayoutManager(layoutManager);

数据适配交由RecyclerView.Adapter，对应方法rv.setAdapter(adapter);

在重写RecyclerView.Adapter(内部使用viewHolder)时，必须实现的三个方法：
    //创建RecyclerView.ViewHolder
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);
    //绑定RecyclerView.ViewHolder 与数据关联
    public abstract void onBindViewHolder(VH holder, int position);
    //item总数量
    public abstract int getItemCount();

 */
