package com.njcool.lzccommon.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.njcool.lzccommon.R;

import java.io.File;

/**
 * Created by lizhichuan on 16/7/6.
 */
public class CoolGlideUtil {

    /**
     * 加载网络图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void urlInto(Context context, String url, ImageView imageView) {
//        if (TextUtils.isEmpty(url)) {
//            return;
//        }
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.img_failure).error(R.mipmap.img_failure)
                .skipMemoryCache(true).centerCrop();
        if (TextUtils.isEmpty(url)) {
            Glide.with(context).
                    load(R.mipmap.img_failure).
                    apply(options).
                    thumbnail(0.1f).
                    into(imageView);
        } else {
            if (url.startsWith("http")) {
                Glide.with(context).load(url).
                        apply(options).
                        thumbnail(0.1f).
                        into(imageView);
            } else {
                Glide.with(context).load(url).
                        apply(options).
                        thumbnail(0.1f).
                        into(imageView);
            }
        }
    }


    public static void urlInto(Context context, String url, ImageView imageView, int failRes) {
        RequestOptions options = new RequestOptions()
                .placeholder(failRes).error(failRes)
                .skipMemoryCache(true).centerCrop();
        if (TextUtils.isEmpty(url)) {
            Glide.with(context).
                    load(failRes).
                    apply(options).
                    thumbnail(0.1f).
                    into(imageView);
        } else {
            if (url.startsWith("http")) {
                Glide.with(context).load(url).
                        apply(options).
                        thumbnail(0.1f).
                        into(imageView);
            } else {
                Glide.with(context).load(url).
                        apply(options).
                        thumbnail(0.1f).
                        into(imageView);
            }
        }
    }


    /**
     * 加载网络图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void urlInto2(Context context, String url, ImageView imageView) {

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.img_failure).error(R.mipmap.img_failure)
                .skipMemoryCache(true).fitCenter();
        if (TextUtils.isEmpty(url)) {
            Glide.with(context).load(R.mipmap.img_failure).
                    apply(options).
                    thumbnail(0.1f).
                    into(imageView);
        } else {
            if (url.startsWith("http")) {
                Glide.with(context).load(url).
                        apply(options).
                        thumbnail(0.1f).
                        into(imageView);
            } else {
                Glide.with(context).load(R.mipmap.img_failure).
                        apply(options).
                        thumbnail(0.1f).
                        into(imageView);
            }
        }
    }

    /**
     * 加载资源图片
     *
     * @param context
     * @param resourceId
     * @param imageView
     */
    public static void ResInto(Context context, int resourceId, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.img_failure).error(R.mipmap.img_failure)
                .skipMemoryCache(true).fitCenter();

        Glide.with(context).load(resourceId).
                apply(options).
                thumbnail(0.1f).
                into(imageView);
    }

    /**
     * 加载本地图片文件
     *
     * @param context
     * @param file
     * @param imageView
     */
    public static void FileInto(Context context, File file, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.img_failure).error(R.mipmap.img_failure)
                .skipMemoryCache(true).fitCenter();
        Glide.with(context).load(file).
                apply(options).
                thumbnail(0.1f).
                into(imageView);
    }

    /**
     * 加载本地图片文件
     *
     * @param context
     * @param path
     * @param imageView
     */
    public static void FileInto(Context context, String path, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.img_failure).error(R.mipmap.img_failure)
                .skipMemoryCache(true).fitCenter();

        File file = new File(path);
        Glide.with(context).load(file).
                apply(options).
                thumbnail(0.1f).
                into(imageView);
    }


}
