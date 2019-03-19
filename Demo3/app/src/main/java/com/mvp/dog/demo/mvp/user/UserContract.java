package com.mvp.dog.demo.mvp.user;



import com.mvp.dog.demo.base.IBaseView;
import com.mvp.dog.demo.model.GankApiBean;
import com.mvp.dog.demo.net.BasicResponse;

import java.util.List;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * Contract: view presenter 管理
 */
public interface UserContract {

    interface View extends IBaseView {
        void showUser(BasicResponse<List<GankApiBean.ResultsBean>> msg);

    }

    interface Presenter {
        void getUser(int page, int size);
    }

}
