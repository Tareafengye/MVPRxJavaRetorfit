package com.mvp.dog.demo.base;



import com.mvp.dog.demo.util.ModelManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * BasePresenter: MVP P 层基类
 */
public abstract class BasePresenter<V extends IBaseView> {

    protected V mView;

    //Disposable容器，收集Disposable，主要用于内存泄漏管理
    private CompositeDisposable mDisposables;

    /**
     * @param view 绑定View
     */
    public <T extends IBaseView> void attachView(T view) {
        this.mView = (V) view;
        mDisposables = new CompositeDisposable();
    }

    /**
     * 解绑关联
     */
    public void detachView() {
        mDisposables.clear();
        mDisposables = null;
        mView = null;
    }

    /**
     * @param disposable 添加Disposable到CompositeDisposable
     *                   通过解除disposable处理内存泄漏问题
     */
    protected boolean addDisposable(Disposable disposable) {
        if (isNullOrDisposed(disposable)) {
            return false;
        }
        return mDisposables.add(disposable);
    }

    /**
     * @param d 判断d是否为空或者dispose
     * @return true:一次任务未开始或者已结束
     */
    protected boolean isNullOrDisposed(Disposable d) {
        return d == null || d.isDisposed();
    }

    /**
     * @param d 判断d是否dispose
     * @return true:一次任务还未结束
     */
    protected boolean isNotDisposed(Disposable d) {
        return d != null && !d.isDisposed();
    }

    /**
     * 获取 Model 实例
     */
    protected <M extends IBaseModel> M getModel(Class<M> clazz) {
        return ModelManager.getInstance().create(clazz);
    }

}
