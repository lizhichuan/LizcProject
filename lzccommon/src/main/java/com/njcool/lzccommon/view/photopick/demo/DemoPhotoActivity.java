package com.njcool.lzccommon.view.photopick.demo;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.njcool.lzccommon.R;
import com.njcool.lzccommon.activity.CoolBaseActivity;
import com.njcool.lzccommon.view.photopick.com.PhotoPicker;
import com.njcool.lzccommon.view.photopick.com.PhotoPreview;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DemoPhotoActivity extends CoolBaseActivity {

    private PhotoAdapter photoAdapter;

    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private ArrayList<String> onLongClickListData = new ArrayList<>();

    private ImageView iv_crop;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_demo_photo);
        findViews();
    }

    @Override
    protected void onClickLeft(View v) {
        super.onClickLeft(v);
        finish();
    }

    private void findViews() {
        setmTopTitle("图片处理");
        iv_crop = (ImageView) findViewById(R.id.iv_crop);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        photoAdapter = new PhotoAdapter(this, selectedPhotos);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);

        findViewById(R.id.button_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoPicker.builder()
                        .setOpenCamera(true)
                        .setCrop(true)
//                        .setCropColors(R.color.colorPrimary, R.color.colorPrimaryDark)
                        .start(DemoPhotoActivity.this);
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setOpenCamera(true)
                        .start(DemoPhotoActivity.this);
            }
        });


        findViewById(R.id.button_one_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setPreviewEnabled(false)
                        .setCrop(true)
                        .setCropXY(1, 1)
//                        .setThemeColors(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorControlNormal)
//                        .setToolbarTitleMarginStart(R.dimen.__picker_toolbar_title_margin_start)
                        .start(DemoPhotoActivity.this);
            }
        });

        findViewById(R.id.button_grid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemoPhotoActivity.this, PreViewImgActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.multiselect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setShowCamera(true)
                        .setPreviewEnabled(true)
                        .setSelected(selectedPhotos)
                        .start(DemoPhotoActivity.this);

            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (photoAdapter.getItemViewType(position) == PhotoAdapter.TYPE_ADD) {
                            PhotoPicker.builder()
                                    .setPhotoCount(PhotoAdapter.MAX)
                                    .setShowCamera(true)
                                    .setPreviewEnabled(true)
                                    .setSelected(selectedPhotos)
                                    .start(DemoPhotoActivity.this);
                        } else {
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(position)
                                    //设置主题色系 toolBar背景色 statusBar颜色 以及toolBar 文本/overflow Icon着色
//                                    .setThemeColors(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorControlNormal)
                                    //设置toolBar标题栏于NavigationIcon的边距
//                                    .setToolbarTitleMarginStart(R.dimen.__picker_toolbar_title_margin_start)
                                    .start(DemoPhotoActivity.this);
                        }
                    }
                }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //选择返回
        if (resultCode == RESULT_OK &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
            iv_crop.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            selectedPhotos.clear();
            if (photos != null) {
                selectedPhotos.addAll(photos);
            }
            photoAdapter.notifyDataSetChanged();
        }
        //拍照功能或者裁剪功能返回
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.CROP_CODE) {
            iv_crop.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            Glide.with(getApplicationContext()).load(Uri.fromFile(new File(data.getStringExtra(PhotoPicker.KEY_CAMEAR_PATH)))).into(iv_crop);
        }
    }


    private PopupWindow pop;


    public void shopPhotoPop() {

        View view = getLayoutInflater().inflate(R.layout.pop_select_photos, null);
        LinearLayout parent = (LinearLayout) view.findViewById(R.id.parent);
        TextView tv_camera = (TextView) view.findViewById(R.id.tv_camera);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_photo = (TextView) view.findViewById(R.id.tv_photo);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
            }
        });
        tv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();

//                //拍照不裁剪
//                PhotoPicker.builder()
//                        .setOpenCamera(true)
//                        .start(DemoPhotoActivity.this);
                //拍照裁剪
                PhotoPicker.builder()
                        .setOpenCamera(true)
                        .setCrop(true)
//                        .setCropColors(R.color.colorPrimary, R.color.colorPrimaryDark)
                        .start(DemoPhotoActivity.this);
            }
        });
        tv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();

//                //多张不裁剪
//                PhotoPicker.builder()
//                        .setPhotoCount(9)
//                        .setShowCamera(true)
//                        .setPreviewEnabled(true)
//                        .setSelected(selectedPhotos)
//                        .start(DemoPhotoActivity.this);
                //单张裁剪
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setPreviewEnabled(false)
                        .setCrop(true)
                        .setCropXY(1, 1)
//                        .setThemeColors(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorControlNormal)
//                        .setToolbarTitleMarginStart(R.dimen.__picker_toolbar_title_margin_start)
                        .start(DemoPhotoActivity.this);

            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setFocusable(true);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.showAtLocation(recyclerView, Gravity.BOTTOM, 0, 0);
    }
    // 预览图片
    //                                    PhotoPreview.builder()
    //                                    .setPhotos(selectedPhotos)
    //                                    .setCurrentItem(position)
    //                                    //设置主题色系 toolBar背景色 statusBar颜色 以及toolBar 文本/overflow Icon着色
    ////                                    .setThemeColors(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorControlNormal)
    //                                    //设置toolBar标题栏于NavigationIcon的边距
    ////                                    .setToolbarTitleMarginStart(R.dimen.__picker_toolbar_title_margin_start)
    //                                    .start(MainActivity.this);
}
