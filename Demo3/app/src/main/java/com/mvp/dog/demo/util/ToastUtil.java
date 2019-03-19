package com.mvp.dog.demo.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * ToastUtil: toast 工具类
 */
public final class ToastUtil {

    private static Toast sToast;

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    /**
     * toast duration = LENGTH_SHORT
     */
    public static void toastS(Context context, @NonNull String info) {
        showToast(context, info, Toast.LENGTH_SHORT);
    }

    /**
     * toast duration = LENGTH_SHORT
     */
    public static void toastS(Context context, @StringRes int info) {
        showToast(context, info, Toast.LENGTH_SHORT);
    }

    /**
     * toast duration = LENGTH_LONG
     */
    public static void toastL(Context context, @NonNull String info) {
        showToast(context, info, Toast.LENGTH_LONG);
    }

    /**
     * toast duration = LENGTH_LONG
     */
    public static void toastL(Context context, @StringRes int info) {
        showToast(context, info, Toast.LENGTH_LONG);
    }

    private static void showToast(final Context context, final CharSequence msg, final int duration) {
        HANDLER.post(new Runnable() {
            @SuppressLint("ShowToast")
            @Override
            public void run() {
                if (sToast == null) {
                    sToast = Toast.makeText(context, msg, duration);
                } else {
                    sToast.setText(msg);
                    sToast.setDuration(duration);
                }
                sToast.show();
            }
        });
    }

    private static void showToast(final Context context, @StringRes final int msg, final int duration) {
        HANDLER.post(new Runnable() {
            @SuppressLint("ShowToast")
            @Override
            public void run() {
                if (sToast == null) {
                    sToast = Toast.makeText(context, msg, duration);
                } else {
                    sToast.setText(msg);
                    sToast.setDuration(duration);
                }
                sToast.show();
            }
        });
    }

}
