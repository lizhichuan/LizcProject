package com.lizc.app.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lizc.app.R;
import com.lizc.app.vo.DocumentBeanVO;
import com.njcool.lzccommon.activity.CoolBaseActivity;
import com.njcool.lzccommon.adapter.CoolCommonRecycleviewAdapter;
import com.njcool.lzccommon.adapter.CoolOnItemClickListener;
import com.njcool.lzccommon.adapter.CoolRecycleViewHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JBDocumentActivity extends CoolBaseActivity {

    @BindView(R.id.rcv)
    RecyclerView rcv;

    private CoolCommonRecycleviewAdapter<DocumentBeanVO.ItemBeanX> adapter;
    private List<DocumentBeanVO.ItemBeanX> mDatas;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jbdocument);
        ButterKnife.bind(this);
        DocumentBeanVO documentBeanVO = new Gson().fromJson(getJson("jb.json", JBDocumentActivity.this), DocumentBeanVO.class);
        linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        adapter = new CoolCommonRecycleviewAdapter<DocumentBeanVO.ItemBeanX>(mDatas, JBDocumentActivity.this,
                R.layout.item_document) {
            @Override
            protected void onBindView(CoolRecycleViewHolder holder, int position) {
                TextView tv_title = holder.getView(R.id.tv_title);
                TextView tv_summary = holder.getView(R.id.tv_summary);
                tv_title.setText(mDatas.get(position).getName());
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < mDatas.get(position).getItem().size(); i++) {
                    sb.append(mDatas.get(position).getItem().get(i).getName());
                    sb.append("    ");
                }
                tv_summary.setText(sb.toString());
            }
        };
        rcv.setAdapter(adapter);

        adapter.setOnItemClickListener(new CoolOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", mDatas.get(position));
                startActivity(JBSencondFloorActivity.class, bundle);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        mDatas = documentBeanVO.getItem();
        adapter.setmDatas(mDatas);
        adapter.notifyDataSetChanged();

    }

    public static String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
