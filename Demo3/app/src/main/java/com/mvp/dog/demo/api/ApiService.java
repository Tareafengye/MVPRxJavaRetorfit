package com.mvp.dog.demo.api;


import com.mvp.dog.demo.model.GankApiBean;
import com.mvp.dog.demo.net.BasicResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *  * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * Descriptions: ApiService
 */
public interface ApiService {


    @GET("福利/{page}/{size}")
    Single<BasicResponse<List<GankApiBean.ResultsBean>>> getUser(@Path("page") int page, @Path("size") int size);

    @GET("orgs/{org}")
    Single<String> getOrg(@Path("org") String org);
}
