package com.lizc.app.network.http.datasource;


import com.lizc.app.network.http.config.LizcHttpConfig;
import com.lizc.app.network.http.datasource.base.INewContentDataSource;
import com.lizc.app.network.http.request.GetHomeRecommendsRequest;
import com.lizc.app.network.http.service.ApiService;
import com.lizc.app.network.model.NewContent;
import com.njcool.lzccommon.network.http.basis.BaseRemoteDataSource;
import com.njcool.lzccommon.network.http.basis.callback.RequestCallback;
import com.njcool.lzccommon.network.viewmodel.base.BaseViewModel;

/**
 * 作者：leavesC
 * 时间：2019/1/30 0:49
 * 描述：
 */
public class NewsContentDataSource extends BaseRemoteDataSource implements INewContentDataSource {

    public NewsContentDataSource(BaseViewModel baseViewModel) {
        super(baseViewModel);
    }

    @Override
    public void getNewContent(GetHomeRecommendsRequest getHomeRecommendsRequest, RequestCallback<NewContent> callback) {
        execute(getService(ApiService.class, LizcHttpConfig.BASE_URL).getNewContents(getHomeRecommendsRequest), callback);
    }

}