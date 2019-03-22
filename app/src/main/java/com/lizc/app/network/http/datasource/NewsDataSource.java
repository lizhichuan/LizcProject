package com.lizc.app.network.http.datasource;


import com.lizc.app.network.http.datasource.base.INewsDataSource;
import com.lizc.app.network.http.service.ApiService;
import com.lizc.app.network.model.NewsPack;
import com.njcool.lzccommon.network.http.basis.BaseRemoteDataSource;
import com.njcool.lzccommon.network.http.basis.callback.RequestCallback;
import com.njcool.lzccommon.network.http.basis.config.HttpConfig;
import com.njcool.lzccommon.network.viewmodel.base.BaseViewModel;

/**
 * 作者：leavesC
 * 时间：2019/1/30 0:49
 * 描述：
 */
public class NewsDataSource extends BaseRemoteDataSource implements INewsDataSource {

    public NewsDataSource(BaseViewModel baseViewModel) {
        super(baseViewModel);
    }

    @Override
    public void getNews(RequestCallback<NewsPack> callback) {
        execute(getService(ApiService.class, HttpConfig.BASE_URL_NEWS).getNews(), callback);
    }

}