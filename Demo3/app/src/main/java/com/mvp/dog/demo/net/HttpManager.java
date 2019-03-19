package com.mvp.dog.demo.net;

import android.support.annotation.NonNull;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * HttpManager: Http 网络请求管理
 */
public final class HttpManager {

    private Retrofit mRetrofit;
    private String mBaseUrl;
    private OkHttpClient mOkHttpClient;
    private Boolean debug = true;

    private static final Logger LOG = Logger.getLogger(HttpManager.class.getName());

    private HttpManager() {
    }

    public static HttpManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * @param mBaseUrl 设置BaseUrl
     *                 放在第一位设置
     */
    public HttpManager setBaseUrl(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
        return Holder.INSTANCE;
    }

    /**
     * 设置OkHttpClient
     */
    public HttpManager setOkHttpClient(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
        return Holder.INSTANCE;
    }

    /**
     * @param retrofit 设置retrofit
     *                 放在最后设置
     */
    public void setRetrofit(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    /**
     * debug
     */
    public HttpManager setDebug(Boolean debug) {
        this.debug = debug;
        return Holder.INSTANCE;
    }

    /**
     * @return mRetrofit.create(clazz)
     */
    public <T> T getApiService(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    /**
     * 自带创建retrofit
     */
    public Retrofit createRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(ObserveOnMainCallAdapterFactory.createMainScheduler())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));
        return builder.build();
    }

    /**
     * @return OkHttpclient
     */
    public OkHttpClient createDefaultClient() {
        final int timeOut = 10;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .proxy(Proxy.NO_PROXY);

        if (debug) {
            builder.addInterceptor(new HttpLoggingInterceptor(new InterceptorLogInfo()));
        }

        return builder.build();
    }

    private static class Holder {
        private static final HttpManager INSTANCE = new HttpManager();
    }

    /**
     * info 等级log
     */
    public static class InterceptorLogInfo implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(@NonNull String message) {
            LOG.log(Level.INFO, message);
        }
    }

}
