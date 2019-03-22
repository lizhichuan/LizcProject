package com.njcool.lzccommon.network.http.service;

import com.njcool.lzccommon.network.http.basis.model.BaseResponseBody;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 作者：leavesC
 * 时间：2018/10/28 13:13
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
public interface FailApiService {


    @GET("leavesC/test1")
    Observable<BaseResponseBody<String>> test1();

    @GET("leavesC/test2")
    Observable<BaseResponseBody<String>> test2();

}
