package com.lizc.app.network.http.service;

import com.lizc.app.network.http.request.GetHomeRecommendsRequest;
import com.lizc.app.network.model.NewContent;
import com.lizc.app.network.model.NewsPack;
import com.njcool.lzccommon.network.http.basis.config.HttpConfig;
import com.njcool.lzccommon.network.http.basis.model.BaseResponseBody;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 作者：leavesC
 * 时间：2018/10/28 13:13
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
public interface ApiService {



    @Headers({HttpConfig.HTTP_REQUEST_TYPE_KEY + ":" + HttpConfig.HTTP_REQUEST_NEWS})
    @GET("toutiao/index")
    Observable<BaseResponseBody<NewsPack>> getNews();

    @POST("index/getNewMessages.app")
    Observable<BaseResponseBody<NewContent>> getNewContents(@Body GetHomeRecommendsRequest getHomeRecommendsRequest);



}
