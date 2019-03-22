package com.lizc.app.network.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.lizc.app.network.http.datasource.NewsContentDataSource;
import com.lizc.app.network.http.repo.NewContentRepo;
import com.lizc.app.network.http.request.GetHomeRecommendsRequest;
import com.lizc.app.network.model.NewContent;
import com.njcool.lzccommon.network.viewmodel.base.BaseViewModel;

public class NewContentViewModel extends BaseViewModel {
    private MutableLiveData<NewContent> newsPackMutableLiveData = new MutableLiveData<>();

    private NewContentRepo newsRepo = new NewContentRepo(new NewsContentDataSource(this));

    public void getNewContent(GetHomeRecommendsRequest getHomeRecommendsRequest) {
        newsRepo.getNewContent(getHomeRecommendsRequest).observe(lifecycleOwner, new Observer<NewContent>() {
            @Override
            public void onChanged(@Nullable NewContent newsPack) {
                newsPackMutableLiveData.setValue(newsPack);
            }

        });
    }

    public MutableLiveData<NewContent> getNewsContentMutableLiveData() {
        return newsPackMutableLiveData;
    }
}
