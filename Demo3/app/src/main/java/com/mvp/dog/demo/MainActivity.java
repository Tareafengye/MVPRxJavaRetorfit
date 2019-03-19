package com.mvp.dog.demo;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mvp.dog.demo.base.BaseActivity;
import com.mvp.dog.demo.base.BaseMvpActivity;
import com.mvp.dog.demo.ui.fragment.HomeFragment;
import com.mvp.dog.demo.ui.fragment.MeFragment;
import com.mvp.dog.demo.ui.fragment.OrderFragment;
import com.mvp.dog.demo.util.BottomBar;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setBarColor(this,112);
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initData() {
        bottomBar.setContainer(R.id.fl_container)
                .setTitleSize(13)
                .setIconHeight(25)
                .setIconWidth(25)
                .setTitleBeforeAndAfterColor("#999999", "#ff5d5e")
                .addItem(HomeFragment.class,
                        "首页",
                        R.drawable.item1_before,
                        R.drawable.item1_after)
                .addItem(OrderFragment.class,
                        "订单",
                        R.drawable.item2_before,
                        R.drawable.item2_after)
                .addItem(MeFragment.class,
                        "我的",
                        R.drawable.item3_before,
                        R.drawable.item3_after)
                .addItem(MeFragment.class,
                        "我的",
                        R.drawable.item3_before,
                        R.drawable.item3_after)
                .build();
    }
}
