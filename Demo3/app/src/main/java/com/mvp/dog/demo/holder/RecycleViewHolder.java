package com.mvp.dog.demo.holder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * Descriptions: RecycleViewHolder
 */
public class RecycleViewHolder extends RecyclerView.ViewHolder {
    //用于缓存已有的界面View
    private SparseArray<View> mViews = new SparseArray<>();

    public RecycleViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        //多次findViewById，对已有的View进行缓存
        View view = mViews.get(viewId);
        //使用缓存的方式减少findViewById的次数
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    //通用的功能进行封装 设置文本 设置条目点击事件 设置图片
    public RecycleViewHolder setText(int viewId, CharSequence text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        //希望能够链式调用
        return this;
    }

    /**
     * 设置图片资源
     *
     * @param viewId
     * @param resourceId
     * @return
     */
    public RecycleViewHolder setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }


}
