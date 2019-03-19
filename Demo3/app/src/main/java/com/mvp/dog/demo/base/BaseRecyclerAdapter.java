package com.mvp.dog.demo.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mvp.dog.demo.holder.RecycleViewHolder;
import com.mvp.dog.demo.iml.MulitiTypeSupport;
import com.mvp.dog.demo.listener.ItemClickListener;

import java.util.List;

/**
 * Describe: RecyclerView的通用Adapter
 * PS: Not easy to write code, please indicate.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> {
    //条目ID不一样那么只能通过参数传递
    private int mLayoutId;
    //通用参数那么只能使用泛型
    private List<T> mDatas;
    //实例化View的LayoutInflate
    private LayoutInflater mInflater;
    private MulitiTypeSupport<T> mTypeSupport;

    public BaseRecyclerAdapter(Context context, List<T> mData, int mLayoutId) {
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = mData;
        this.mLayoutId = mLayoutId;
    }

    public BaseRecyclerAdapter(Context context, List<T> mDatas, MulitiTypeSupport<T> typeSupport) {
        this(context, mDatas, -1);
        this.mTypeSupport = typeSupport;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mTypeSupport != null) {
            //需要多布局
            mLayoutId = viewType;
        }

        //创建View context
        View itemView = mInflater.inflate(mLayoutId, parent, false);
        //实例化View的方式三种
        //View.inflate(mContext, mLayoutId, null);
        //LayoutInflater.from(mContext).inflate(mLayoutId, parent);
        //LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new RecycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, final int position) {
        //ViewHolder的优化
        convert(holder, mDatas.get(position), position);
        //条目点击事件
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(v, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mItemClickListener.onItemLongClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mTypeSupport != null) {
            return mTypeSupport.getLayoutId(mDatas.get(position));
        }
        return super.getItemViewType(position);
    }

    /**
     * 把必要参数传递出去
     *
     * @param holder
     * @param item     当前数据
     * @param position 当前位置索引
     */
    protected abstract void convert(RecycleViewHolder holder, T item, int position);


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 设置点击事件回调
     */
    private ItemClickListener mItemClickListener;

    public void setmItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
