package com.mvp.dog.demo.mvp.model;


import com.mvp.dog.demo.api.ApiService;
import com.mvp.dog.demo.base.IBaseModel;
import com.mvp.dog.demo.net.HttpManager;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * BaseModel: 实现IBaseModel基类Model，项目里的请求数据model可以继承BaseModel
 * BaseModel里可以定义Model公共方法
 */
public abstract class BaseModel implements IBaseModel {

    private ApiService apiService;

    protected ApiService getApiService() {
        if (apiService == null) {
            apiService = HttpManager.getInstance().getApiService(ApiService.class);
        }
        return apiService;
    }

}
