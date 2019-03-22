package com.lizc.app.network.http.repo;

import android.arch.lifecycle.MutableLiveData;

import com.lizc.app.network.http.datasource.base.INewsDataSource;
import com.lizc.app.network.model.NewsPack;
import com.njcool.lzccommon.network.http.basis.BaseRepo;
import com.njcool.lzccommon.network.http.basis.callback.RequestCallback;


/**
 * 作者：leavesC
 * 时间：2019/1/30 0:51
 * 描述：
 */
public class NewsRepo extends BaseRepo<INewsDataSource> {

    public NewsRepo(INewsDataSource remoteDataSource) {
        super(remoteDataSource);
    }

    public MutableLiveData<NewsPack> getNews() {
        final MutableLiveData<NewsPack> newsPackMutableLiveData = new MutableLiveData<>();
        remoteDataSource.getNews(new RequestCallback<NewsPack>() {
            @Override
            public void onSuccess(NewsPack newsPack) {
                newsPackMutableLiveData.setValue(newsPack);
            }
        });
        return newsPackMutableLiveData;
    }

}
