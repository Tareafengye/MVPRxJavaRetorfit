package com.mvp.dog.demo.mvp.user;


import com.mvp.dog.demo.mvp.model.BaseModel;
import com.mvp.dog.demo.net.BasicResponse;
import com.mvp.dog.demo.net.HttpResultObserver;

import io.reactivex.disposables.Disposable;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * UserModel: GankApi信息相关的model
 */
public class UserModel extends BaseModel {

    public Disposable getUser(int page,int size, HttpResultObserver<BasicResponse> observer) {
        return getApiService().getUser(page,size).subscribeWith(observer);
    }

}
