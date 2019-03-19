package com.mvp.dog.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mvp.dog.demo.base.BasePresenter;
import com.mvp.dog.demo.base.IBaseView;
import com.mvp.dog.demo.iml.IView;
import com.mvp.dog.demo.util.KnifeKit;
import com.mvp.dog.demo.weight.LoadingDailog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/15
 * Descriptions: BaseFragemnt
 */
public abstract class BaseMvpFragment<P extends BasePresenter<? extends IBaseView>> extends Fragment implements IBaseView ,IView,View.OnClickListener {

    //主Presenter
    protected P mPresenter;
    //多个Presenter时候需要的容器
    private ArraySet<BasePresenter> mPresenters = new ArraySet<>(4);
    protected LayoutInflater layoutInflater;
    private View rootView;
    private Unbinder unbinder;
    private LoadingDailog mProgressDialog;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();
        initLoading();
        mPresenter = getPresenter();
        addToPresenters(mPresenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layoutInflater = inflater;
        if (rootView == null && initView() > 0) {
            rootView = inflater.inflate(initView(), null);
            bindUI(rootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }

        return rootView;
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this, rootView);
    }

    @Override
    public void onDestroy() {
        for (BasePresenter presenter : mPresenters) {
            presenter.detachView();
        }
        mPresenters.clear();
        super.onDestroy();
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(String msg) {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(getActivity())
                .setMessage(msg)
                .setCancelable(true)
                .setMessage(msg)
                .setCancelOutside(true);
        mProgressDialog = loadBuilder.create();
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


    public abstract int initView();

    /**
     * 初始化Presenter，其他多个Presenter也在该方法创建并调用addToPresenters加入到集合
     * @return 主Presenter
     */
    protected abstract P getPresenter();

    /**
     * 根据具体项目需求创建loading
     */
    protected void initLoading() {

    }



    /**
     * 初始化Listener
     */
    protected abstract void widgetClick(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 把其他的Presenter添加到Presenters集合里
     * 这样会自动绑定View和管理内存释放
     */
    protected <T extends BasePresenter> void addToPresenters(T presenter) {
        presenter.attachView(this);
        mPresenters.add(presenter);
    }

    @Override
    public void onClick(View v) {
        if (fastClick())
            widgetClick(v);

    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

}
