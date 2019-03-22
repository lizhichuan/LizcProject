package com.lizc.app.activity;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lizc.app.R;
import com.lizc.app.network.http.request.GetHomeRecommendsRequest;
import com.lizc.app.network.model.NewContent;
import com.lizc.app.network.viewmodel.NewContentViewModel;
import com.njcool.lzccommon.activity.CoolBaseActivity;
import com.njcool.lzccommon.activity.CoolWebViewActivity;
import com.njcool.lzccommon.adapter.CoolCommonRecycleviewAdapter;
import com.njcool.lzccommon.adapter.CoolOnItemClickListener;
import com.njcool.lzccommon.adapter.CoolRecycleViewHolder;
import com.njcool.lzccommon.network.viewmodel.base.LViewModelProviders;
import com.njcool.lzccommon.recycleview.interfaces.OnLoadMoreListener;
import com.njcool.lzccommon.recycleview.interfaces.OnRefreshListener;
import com.njcool.lzccommon.recycleview.recyclerview.LRecyclerView;
import com.njcool.lzccommon.recycleview.recyclerview.LRecyclerViewAdapter;
import com.njcool.lzccommon.recycleview.recyclerview.ProgressStyle;
import com.njcool.lzccommon.utils.CoolGlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewContentActivity extends CoolBaseActivity {


    @BindView(R.id.rcv_content)
    LRecyclerView rcvContent;

    private NewContentViewModel newContentViewModel;

    private LinearLayoutManager linearLayoutManager;
    private CoolCommonRecycleviewAdapter<NewContent.MessagesBean> adapter;
    private List<NewContent.MessagesBean> mDatas;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content);
        ButterKnife.bind(this);
        findViews();
    }

    @Override
    protected ViewModel initViewModel() {
        newContentViewModel = LViewModelProviders.of(this, NewContentViewModel.class);
        newContentViewModel.getNewsContentMutableLiveData().observe(this, this::handlerNews);
        return newContentViewModel;
    }

    private void findViews() {
//        rcvContent = (LRecyclerView) findViewById(R.id.rcv_content);
        linearLayoutManager = new LinearLayoutManager(this);
        rcvContent.setLayoutManager(linearLayoutManager);
        adapter = new CoolCommonRecycleviewAdapter<NewContent.MessagesBean>(NewContentActivity.this,
                mDatas, R.layout.item_new_content) {
            @Override
            protected void onBindView(CoolRecycleViewHolder holder, int position) {
                ImageView img_pic = holder.getView(R.id.img_pic);
                TextView tv_title = holder.getView(R.id.tv_title);
                TextView tv_time = holder.getView(R.id.tv_time);


                CoolGlideUtil.urlInto(NewContentActivity.this, mDatas.get(position).getCoverImg(), img_pic);
                if (TextUtils.isEmpty(mDatas.get(position).getTitle())) {
                    tv_title.setText(mDatas.get(position).getIntro());
                } else {
                    tv_title.setText(mDatas.get(position).getTitle());
                }
                tv_time.setText(mDatas.get(position).getPublishTime());
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        rcvContent.setAdapter(mLRecyclerViewAdapter);
        rcvContent.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        rcvContent.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        rcvContent.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        rcvContent.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                queryNews(true);

            }
        });
        //是否禁用自动加载更多功能,false为禁用, 默认开启自动加载更多功能
        rcvContent.setLoadMoreEnabled(true);
        rcvContent.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                queryNews(false);

            }
        });
        //设置头部加载颜色
        rcvContent.setHeaderViewColor(R.color.common_black, R.color.common_black, R.color.line_gray);
        //设置底部加载颜色
        rcvContent.setFooterViewColor(R.color.common_black, R.color.common_black, R.color.line_gray);
        //设置底部加载文字提示
        rcvContent.setFooterViewHint("拼命加载中", "我是有底线的", "网络不给力啊，点击再试一次吧");
        rcvContent.refresh();
        adapter.setOnItemClickListener(new CoolOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("url", mDatas.get(position).getMessageUrl() + "?messageId=" + mDatas.get(position).getId()
                        + "&visitorId=2048");
                startActivity(CoolWebViewActivity.class, bundle);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    private void handlerNews(NewContent newContent) {
        if (newContent.getMessages() != null && newContent.getMessages().size() > 0) {
            if (page == 1) {
                mDatas = newContent.getMessages();
            } else {
                for (int i = 0; i < newContent.getMessages().size(); i++) {
                    mDatas.add(newContent.getMessages().get(i));
                }
            }
            adapter.setmDatas(mDatas);
            adapter.notifyDataSetChanged();
            rcvContent.refreshComplete(30);
            if (newContent.getPageBean().getTotalPages() <= page) {
                rcvContent.setNoMore(true);
            }
        } else {
            if (page == 1) {
//                                    CoolPublicMethod.Toast(getActivity(), "暂无数据");
            }
        }
    }

    public void queryNews(boolean refresh) {
        if (refresh) {
            page = 1;
        } else {
            page++;
        }
        GetHomeRecommendsRequest request = new GetHomeRecommendsRequest();
        request.setCustomerId("2048");
        request.setPageNo(page);
        request.setPageSize(30);
        newContentViewModel.getNewContent(request);
    }
}
