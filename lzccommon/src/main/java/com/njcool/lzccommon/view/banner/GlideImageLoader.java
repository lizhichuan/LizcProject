package com.njcool.lzccommon.view.banner;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.njcool.lzccommon.R;
import com.njcool.lzccommon.view.banner.loader.ImageLoader;

/**
 * Created by lw on 2017-09-01.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.img_failure).error(R.mipmap.img_failure)
                .skipMemoryCache(false).fitCenter();

        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext())
                .load(path).
                apply(options).
                into(imageView);
    }
}
