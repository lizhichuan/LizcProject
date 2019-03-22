package com.lizc.app.network.http.repo;

import android.arch.lifecycle.MutableLiveData;

import com.lizc.app.network.http.datasource.base.INewContentDataSource;
import com.lizc.app.network.http.request.GetHomeRecommendsRequest;
import com.lizc.app.network.model.NewContent;
import com.njcool.lzccommon.network.http.basis.BaseRepo;
import com.njcool.lzccommon.network.http.basis.callback.RequestCallback;

public class NewContentRepo extends BaseRepo<INewContentDataSource> {

    public NewContentRepo(INewContentDataSource remoteDataSource) {
        super(remoteDataSource);
    }

    public MutableLiveData<NewContent> getNewContent(GetHomeRecommendsRequest getHomeRecommendsRequest) {
        final MutableLiveData<NewContent> newsPackMutableLiveData = new MutableLiveData<>();
        remoteDataSource.getNewContent(getHomeRecommendsRequest, new RequestCallback<NewContent>() {
            @Override
            public void onSuccess(NewContent newContent) {
                newsPackMutableLiveData.setValue(newContent);
            }
        });
        return newsPackMutableLiveData;
    }

}
