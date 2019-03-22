package com.njcool.lzccommon.network.http.datasource;


import com.njcool.lzccommon.network.http.basis.BaseRemoteDataSource;
import com.njcool.lzccommon.network.http.basis.callback.RequestCallback;
import com.njcool.lzccommon.network.http.datasource.base.IFailExampleDataSource;
import com.njcool.lzccommon.network.http.service.FailApiService;
import com.njcool.lzccommon.network.viewmodel.base.BaseViewModel;

/**
 * 作者：leavesC
 * 时间：2019/1/30 13:02
 * 描述：
 */
public class FailExampleDataSource extends BaseRemoteDataSource implements IFailExampleDataSource {

    public FailExampleDataSource(BaseViewModel baseViewModel) {
        super(baseViewModel);
    }

    @Override
    public void test1(RequestCallback<String> callback) {
        execute(getService(FailApiService.class).test1(), callback);
    }

    @Override
    public void test2(RequestCallback<String> callback) {
        execute(getService(FailApiService.class).test2(), callback);
    }

}