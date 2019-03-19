

package com.mvp.dog.demo.ui.fragment;

import android.view.View;
import android.widget.ListView;


import com.mvp.dog.demo.R;
import com.mvp.dog.demo.base.BaseMvpFragment;
import com.mvp.dog.demo.model.GankApiBean;
import com.mvp.dog.demo.mvp.user.UserContract;
import com.mvp.dog.demo.mvp.user.UserPresenter;
import com.mvp.dog.demo.net.BasicResponse;

import java.util.List;

import butterknife.BindView;


public class MeFragment extends BaseMvpFragment<UserPresenter> implements UserContract.View {
    private UserPresenter mOrgPresenter;


    @Override
    public int initView() {
        return R.layout.fragment_me;
    }

    @Override
    protected UserPresenter getPresenter() {
        mOrgPresenter = new UserPresenter();
        addToPresenters(mOrgPresenter);
        return new UserPresenter();
    }


    @Override
    protected void widgetClick(View view) {

    }


    @Override
    protected void initData() {

    }


    @Override
    public void showUser(BasicResponse<List<GankApiBean.ResultsBean>> msg) {

    }
}
