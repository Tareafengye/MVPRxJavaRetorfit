package com.mvp.dog.demo.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;


import com.mvp.dog.demo.R;
import com.mvp.dog.demo.adapter.GankAdapter;
import com.mvp.dog.demo.base.BaseMvpFragment;
import com.mvp.dog.demo.model.GankApiBean;
import com.mvp.dog.demo.mvp.user.UserContract;
import com.mvp.dog.demo.mvp.user.UserPresenter;
import com.mvp.dog.demo.net.BasicResponse;
import com.mvp.dog.demo.util.DensityUtil;
import com.mvp.dog.demo.util.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class HomeFragment extends BaseMvpFragment<UserPresenter> implements UserContract.View {
    private String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.tv_shape)
    TextView tv_shape;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private UserPresenter mOrgPresenter;
    private GankAdapter adapter;
    private List<GankApiBean.ResultsBean> list = new ArrayList<>();

    @Override
    public int initView() {
        return R.layout.fragment_home;
    }

    @Override
    protected UserPresenter getPresenter() {
        mOrgPresenter = new UserPresenter();
        addToPresenters(mOrgPresenter);
        return new UserPresenter();

    }

    @OnClick(R.id.tv_city)
    @Override
    protected void widgetClick(View view) {

        switch (view.getId()) {
            case R.id.tv_city:
//                showLoading("狗子加载中...");
                //点击城市按钮进行网络请求
                mPresenter.getUser(30, 1);
                break;
        }
    }


    @Override
    protected void initData() {
        init();
        //开始请求网络
        mPresenter.getUser(30, 1);
    }

    /**
     * 初始化
     */
    private void init() {

        tv_shape.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv_shape.setText("请搜索关键字");
        recycler.setHasFixedSize(true);
        recycler.addItemDecoration(new GridSpacingItemDecoration(4,
                DensityUtil.dip2px(getActivity(), 2), false));
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        // 解决调用 notifyItemChanged 闪烁问题,取消默认动画
        ((SimpleItemAnimator) recycler.getItemAnimator())
                .setSupportsChangeAnimations(false);

    }

    /***
     * 返回数据展示UI
     * @param msg
     */
    @Override
    public void showUser(BasicResponse<List<GankApiBean.ResultsBean>> msg) {
//        hideLoading();
        adapter = new GankAdapter(getActivity(), msg.getResults(), R.layout.item_gank_api);
        recycler.setAdapter(adapter);
    }
}
