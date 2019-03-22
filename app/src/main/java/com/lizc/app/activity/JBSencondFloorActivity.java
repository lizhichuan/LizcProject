package com.lizc.app.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lizc.app.R;
import com.lizc.app.vo.DocumentBeanVO;
import com.njcool.lzccommon.activity.CoolBaseActivity;
import com.njcool.lzccommon.adapter.CoolCommonRecycleviewAdapter;
import com.njcool.lzccommon.adapter.CoolRecycleViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JBSencondFloorActivity extends CoolBaseActivity {

    @BindView(R.id.rcv)
    RecyclerView rcv;

    private DocumentBeanVO.ItemBeanX bean;

    private CoolCommonRecycleviewAdapter<DocumentBeanVO.ItemBeanX.ItemBean> adapter;
    private List<DocumentBeanVO.ItemBeanX.ItemBean> mDatas;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jbsencond_floor);
        ButterKnife.bind(this);
        bean = (DocumentBeanVO.ItemBeanX) getIntent().getSerializableExtra("bean");
        linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        adapter = new CoolCommonRecycleviewAdapter<DocumentBeanVO.ItemBeanX.ItemBean>(JBSencondFloorActivity.this,
                mDatas, R.layout.item_document) {
            @Override
            protected void onBindView(CoolRecycleViewHolder holder, int position) {
                TextView tv_title = holder.getView(R.id.tv_title);
                TextView tv_summary = holder.getView(R.id.tv_summary);
                TextView tv_header = holder.getView(R.id.tv_header);
                tv_header.setVisibility(View.VISIBLE);
                tv_title.setText(mDatas.get(position).getName());
                StringBuffer sb2 = new StringBuffer();
                for (int i = 8; i < mDatas.get(position).getRequest().getHeader().size() - 4; i++) {
                    sb2.append(mDatas.get(position).getRequest().getHeader().get(i).getKey() + ":")
                            .append(mDatas.get(position).getRequest().getHeader().get(i).getValue()).append("  ");
                }
                tv_header.setText(sb2.toString());
                StringBuffer sb = new StringBuffer();
                sb.append(mDatas.get(position).getRequest().getUrl().getRaw()).append("\n");

                if (mDatas.get(position).getRequest().getBody() != null && mDatas.get(position).getRequest().getBody().getUrlencoded() != null) {
                    sb.append("入参1  ");
                    for (int i = 0; i < mDatas.get(position).getRequest().getBody().getUrlencoded().size(); i++) {
                        sb.append("Key:" + mDatas.get(position).getRequest().getBody().getUrlencoded().get(i).getKey())
                                .append("  ,Type:").append(mDatas.get(position).getRequest().getBody().getUrlencoded().get(i).getType())
                                .append("  ,Value:").append(mDatas.get(position).getRequest().getBody().getUrlencoded().get(i).getValue());
                        sb.append("\n");
                    }
                }

                if (mDatas.get(position).getRequest().getBody() != null && mDatas.get(position).getRequest().getBody().getFormdata() != null) {
                    sb.append("入参2  ");
                    for (int i = 0; i < mDatas.get(position).getRequest().getBody().getFormdata().size(); i++) {
                        sb.append("Key:" + mDatas.get(position).getRequest().getBody().getFormdata().get(i).getKey())
                                .append("  ,Type:").append(mDatas.get(position).getRequest().getBody().getFormdata().get(i).getType())
                                .append("  ,Value:").append(mDatas.get(position).getRequest().getBody().getFormdata().get(i).getValue());
                        sb.append("\n");
                    }
                }
                sb.append("\n");
                sb.append(mDatas.get(position).getRequest().getDescription());
                tv_summary.setText(sb.toString());
            }
        };
        rcv.setAdapter(adapter);
        mDatas = bean.getItem();
        adapter.setmDatas(mDatas);
        adapter.notifyDataSetChanged();
    }
}
