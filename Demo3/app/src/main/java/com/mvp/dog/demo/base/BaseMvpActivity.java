package com.mvp.dog.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArraySet;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * BaseMvpActivity: MVP activity 基类
 */
public abstract class BaseMvpActivity<P extends BasePresenter<? extends IBaseView>> extends BaseActivity implements IBaseView {

    //主Presenter
    protected P mPresenter;
    //多个Presenter时候需要的容器
    private ArraySet<BasePresenter> mPresenters = new ArraySet<>(4);

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        initLoading();
        mPresenter = getPresenter();
        addToPresenters(mPresenter);
        initView();
        initListener();
        initData();
    }

    @Override
    protected void onDestroy() {
        for (BasePresenter presenter : mPresenters) {
            presenter.detachView();
        }
        mPresenters.clear();
        super.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String msg) {
        toastS(msg);
    }

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
     * 初始化View
     */
    protected void initView(){

    }

    /**
     * 初始化Listener
     */
    protected abstract void initListener();

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

}
