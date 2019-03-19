package com.mvp.dog.demo.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;


import com.mvp.dog.demo.util.BarUtils;
import com.mvp.dog.demo.util.Logger;
import com.mvp.dog.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * BaseActivity: Activity 基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    //布局View, 要是使用 DataBinding 可能会用到
    protected View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = LayoutInflater.from(this).inflate(getLayoutId(), null);
        setContentView(mView);
        ButterKnife.bind(this);
        init(savedInstanceState);
    }
    /**
     * 设置barcolor状态栏颜色属性
     *
     * @param activity
     * @param color
     */
    public void setBarColor(Activity activity, int color) {
        BarUtils.setStatusBarAlpha(activity, color);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Logger.i(this.getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.i(this.getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.i(this.getClass().getSimpleName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.i(this.getClass().getSimpleName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.i(this.getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        Logger.i(this.getClass().getSimpleName());
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        List<String> granted = new ArrayList<>();// 允许的权限
        List<String> denied = new ArrayList<>();//拒绝的权限

        for (int i = 0, k = permissions.length; i < k; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(permissions[i]);
            } else {
                denied.add(permissions[i]);
            }
        }

        //允许权限
        if (granted.size() > 0) {
            onPermissionGranted(requestCode, granted);
        }

        //拒绝权限
        if (denied.size() > 0) {
            onPermissionsDenied(requestCode, denied);
        }

        //所有权限通过
        if (granted.size() > 0 && denied.size() == 0) {
            onAllPermissionsGranted(requestCode);
        }
    }

    /**
     * 初始化
     */
    protected abstract void init(@Nullable Bundle savedInstanceState);

    /**
     * @return layoutId
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 所以权限通过
     */
    protected void onAllPermissionsGranted(int requestCode) {

    }

    /**
     * 被拒绝的权限，一般需要提示用户，比如
     * 1、拒绝后重新继续申请权限
     * 2、用户点击了“不再提醒”则提示手动去开启
     * 3、被拒绝的权限不重要，不用管了
     */
    protected void onPermissionsDenied(int requestCode, List<String> denied) {
        //默认判断是否需要提示用户手动开启权限
        /*if (PermissionUtil.checkedPermanentlyDenied(this, denied)) {
            DialogUtils.showConfirmDialog(BaseActivity.this, "权限提示", "请设置相关权限，点击\"确定\"进行设置",
                    "取消", null,
                    "确定", (dialog, which) -> {
                        PermissionUtil.openSetting(BaseActivity.this);
                    });
        }*/
    }

    /**
     * 通过的权限
     */
    protected void onPermissionGranted(int requestCode, List<String> granted) {

    }

    /**
     * @param clazz 启动activity
     */
    public void start(@NonNull Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * @param clazz 启动activity for Result
     */
    public void startForResult(@NonNull Class<? extends Activity> clazz, @IntRange(to = 0xffff) int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * @param clazz 启动activity
     */
    public void start(@NonNull Class<? extends Activity> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * @param clazz 启动activity for Result
     *           @param extras 通过
     */
    public void startForResult(@NonNull Class<? extends Activity> clazz, @IntRange(to = 0xffff) int requestCode, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivityForResult(intent, requestCode);
    }

    /**
     * @return 带有 extras 启动 activity 的 Bundle 数据
     */
    @Nullable
    public Bundle getExtras() {
        return getIntent().getExtras();
    }

    public void toastS(@NonNull String info) {
        ToastUtil.toastS(this, info);
    }

    public void toastS(@StringRes int info) {
        ToastUtil.toastS(this, info);
    }

    public void toastL(@NonNull String info) {
        ToastUtil.toastL(this, info);
    }

    public void toastL(@StringRes int info) {
        ToastUtil.toastL(this, info);
    }

}
