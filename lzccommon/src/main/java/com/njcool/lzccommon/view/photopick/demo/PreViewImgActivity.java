package com.njcool.lzccommon.view.photopick.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.njcool.lzccommon.R;
import com.njcool.lzccommon.activity.CoolBaseActivity;
import com.njcool.lzccommon.view.photopick.com.PhotoPreview;
import com.njcool.lzccommon.view.photopick.com.event.PhotoOnLongClick;
import com.njcool.lzccommon.view.photopick.com.event.PhotoOnLongClickManager;

import java.util.ArrayList;

public class PreViewImgActivity extends CoolBaseActivity {

    private GridView img_grid;
    ArrayList<String> imgData = new ArrayList<>();
    private ArrayList<String> onLongClickListData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_pre_view_img);
        findViews();
    }

    @Override
    protected void onClickLeft(View v) {
        super.onClickLeft(v);
        finish();
    }

    private void findViews() {
        setmTopTitle("网图预览");
        onLongClickListData.add("分享");
        onLongClickListData.add("保存");
        onLongClickListData.add("取消");
        //图片长按后的item点击事件回调
        PhotoOnLongClickManager photoOnLongClickManager = PhotoOnLongClickManager.getInstance();
        photoOnLongClickManager.setOnLongClickListener(new PhotoOnLongClick() {
            @Override
            public void sendOnLongClick(int position, String path) {
                Toast.makeText(PreViewImgActivity.this, "你点击了：" + onLongClickListData.get(position) + "，图片路径：" + path, Toast.LENGTH_LONG).show();
            }
        });
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/61PI88GEqTL.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/61m2kJWam5L.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/61dQGK0xeuL.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/71YuPpF6jKL.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/615YYN4q7gL.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/61Nriqwg2LL.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/615b9gM9GgL.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/41fi2pEYkuL.jpg");
        img_grid = (GridView) findViewById(R.id.img_grid);
        PreViewGridAdapter gridAdapter = new PreViewGridAdapter(PreViewImgActivity.this, imgData);
        img_grid.setAdapter(gridAdapter);
        img_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                PhotoPreview.builder()
                        .setPhotos(imgData)
                        .setCurrentItem(position)
                        .setShowToolbar(false)
                        .setOnLongClickListData(onLongClickListData)
                        .start(PreViewImgActivity.this);
            }
        });
    }
}
