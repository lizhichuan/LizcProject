package com.lizc.app.network.http.datasource.base;


import com.lizc.app.network.http.request.GetHomeRecommendsRequest;
import com.lizc.app.network.model.NewContent;
import com.njcool.lzccommon.network.http.basis.callback.RequestCallback;

/**
 * 作者：leavesC
 * 时间：2019/1/30 0:46
 * 描述：
 */
public interface INewContentDataSource {

    void getNewContent(GetHomeRecommendsRequest getHomeRecommendsRequest, RequestCallback<NewContent> callback);

}
