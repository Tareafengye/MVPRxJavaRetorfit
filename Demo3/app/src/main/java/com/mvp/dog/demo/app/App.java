package com.mvp.dog.demo.app;

import android.app.Application;


import com.mvp.dog.demo.BuildConfig;
import com.mvp.dog.demo.api.ConfigApi;
import com.mvp.dog.demo.net.HttpManager;


/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/15
 * Descriptions: App
 */
public class App extends Application{
    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        HttpManager.getInstance()
                .setBaseUrl(ConfigApi.URL)
                .setDebug(BuildConfig.DEBUG)
                .setOkHttpClient(HttpManager.getInstance().createDefaultClient())
                .setRetrofit(HttpManager.getInstance().createRetrofit());
    }

    public static App getInstance(){

        return instance;
    }
}
