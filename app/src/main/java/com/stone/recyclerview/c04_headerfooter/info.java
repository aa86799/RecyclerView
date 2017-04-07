package com.stone.recyclerview.c04_headerfooter;


/*
RecyclerView 添加 header 、footer
这里基于 03_abstract包中的 抽象类；

    使用BaseAdapter#addHeaderView()  、BaseAdapter#addFooterView()

    重写BaseAdapter
        public int getCustomViewType(int position); 根据position定义viewType

        public BaseHolder createCustomViewHolder(ViewGroup parent, int viewType); 根据viewType，创建不同的holder

        public void bindCustomViewHolder(BaseHolder holder, final int position);
            内部可使用getItemViewType(position)， 来处理 viewType 对应的 holder
 */
