package com.mvp.dog.demo.mvp.user;

import android.text.TextUtils;


import com.mvp.dog.demo.base.BasePresenter;
import com.mvp.dog.demo.net.BasicResponse;
import com.mvp.dog.demo.net.HttpResultObserver;

import io.reactivex.disposables.Disposable;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * MainPresenter:
 */
public class UserPresenter extends BasePresenter<UserContract.View> implements UserContract.Presenter {

    private UserModel mModel;

    public UserPresenter() {
        mModel = getModel(UserModel.class);
    }

    @Override
    public void getUser(int page,int size) {

//        if (TextUtils.isEmpty(userName)) {
//            mView.showMsg("用户名不能为空");
//            return;
//        }

        Disposable disposable = mModel.getUser(page,size,new HttpResultObserver<BasicResponse>() {
            @Override
            protected void onResult(BasicResponse s) {
                mView.showUser(s);
                mView.hideLoading();
            }

            @Override
            protected void onError(BasicResponse response) {
                mView.hideLoading();
                if (response.isError()==true){
                    //返回错误结果，在这儿提示
                }
            }


        });
        addDisposable(disposable);
    }
}
