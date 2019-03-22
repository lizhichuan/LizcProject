package com.lizc.app.network.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.lizc.app.network.http.datasource.NewsDataSource;
import com.lizc.app.network.http.repo.NewsRepo;
import com.lizc.app.network.model.NewsPack;
import com.njcool.lzccommon.network.viewmodel.base.BaseViewModel;


/**
 * 作者：leavesC
 * 时间：2019/1/30 0:50
 * 描述：
 */
public class NewsViewModel extends BaseViewModel {

    private MutableLiveData<NewsPack> newsPackMutableLiveData = new MutableLiveData<>();

    private NewsRepo newsRepo = new NewsRepo(new NewsDataSource(this));

    public void getNews() {
        newsRepo.getNews().observe(lifecycleOwner, new Observer<NewsPack>() {
            @Override
            public void onChanged(@Nullable NewsPack newsPack) {
                newsPackMutableLiveData.setValue(newsPack);
            }
        });
    }

    public MutableLiveData<NewsPack> getNewsPackMutableLiveData() {
        return newsPackMutableLiveData;
    }

}
