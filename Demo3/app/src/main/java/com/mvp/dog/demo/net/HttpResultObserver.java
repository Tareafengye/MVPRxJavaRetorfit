package com.mvp.dog.demo.net;

import android.text.TextUtils;


import com.google.gson.JsonParseException;
import com.mvp.dog.demo.app.App;
import com.mvp.dog.demo.util.ToastUtil;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.HttpException;


/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * HttpResultSubscriber: 数据回调处理
 */
public abstract class HttpResultObserver<T extends BasicResponse> extends DisposableSingleObserver<T> {

    @Override
    public void onSuccess(T response) {

        dispose();

        if (response.isError() == false) {
            onResult(response);
        } else {
            onError(response);
        }
    }

    @Override
    public void onError(Throwable e) {
        dispose();
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
    }

    /**
     * @param t 获取结果
     */
    protected abstract void onResult(T t);

    protected  abstract void onError(T t);
    /***
     * 特殊处理，暂时未绑定任何数据
     * @param response
     */

    public void onFail(T response) {
        boolean message = response.isError();

    }

    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                ToastUtil.toastL(App.getInstance(),"网络连接失败,请检查网络");
                break;

            case CONNECT_TIMEOUT:
                ToastUtil.toastL(App.getInstance(),"链接超时,请稍后重试");
                break;

            case BAD_NETWORK:
                ToastUtil.toastL(App.getInstance(),"服务器异常");
                break;

            case PARSE_ERROR:
                ToastUtil.toastL(App.getInstance(),"解析服务器数据失败");
                break;

            case UNKNOWN_ERROR:
            default:
                ToastUtil.toastL(App.getInstance(),"未知错误");
                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
