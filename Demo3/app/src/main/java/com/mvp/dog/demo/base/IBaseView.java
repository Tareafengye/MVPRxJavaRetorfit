package com.mvp.dog.demo.base;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * IBaseView: MVP V 层Base接口定义
 */
public interface IBaseView {

    /**
     * @param msg 显示文本信息
     */
    void showMsg(String msg);

    /**
     * 显示loading
     */
    void showLoading();

    /**
     * 带文字信息显示loading
     */
    void showLoading(String msg);

    /**
     * 隐藏loading
     */
    void hideLoading();

}
