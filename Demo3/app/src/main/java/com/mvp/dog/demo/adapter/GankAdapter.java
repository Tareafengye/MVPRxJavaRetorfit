package com.mvp.dog.demo.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mvp.dog.demo.R;
import com.mvp.dog.demo.app.App;
import com.mvp.dog.demo.base.BaseRecyclerAdapter;
import com.mvp.dog.demo.holder.RecycleViewHolder;
import com.mvp.dog.demo.model.GankApiBean;

import java.util.List;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * Descriptions: GankAdapter
 */
public class GankAdapter extends BaseRecyclerAdapter<GankApiBean.ResultsBean>{
    public GankAdapter(Context context, List<GankApiBean.ResultsBean> mData, int mLayoutId) {
        super(context, mData, mLayoutId);
    }

    @Override
    protected void convert(RecycleViewHolder holder, GankApiBean.ResultsBean item, int position) {

        ImageView iv_gank=holder.getView(R.id.iv_gank);
        Glide.with(holder.itemView.getContext())
                .asBitmap()
                .load(item.getUrl())
                .into(iv_gank);

    }
}
