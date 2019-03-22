package com.njcool.lzccommon.activity;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njcool.lzccommon.R;
import com.njcool.lzccommon.app.App;
import com.njcool.lzccommon.utils.CoolLogTrace;
import com.njcool.lzccommon.utils.CoolPublicMethod;
import com.njcool.lzccommon.utils.CoolStatusBarUtil;
import com.njcool.lzccommon.vo.BaseVO;
import com.njcool.lzccommon.vo.NetWorkVO;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * Cool 基类
 */
public class CoolBaseActivity extends BaseActivity {
    public TextView mTopTitle, tv_right;
    public ImageView mTopLeft;
    public ImageView mTopRight;
    public LinearLayout ly_content, linear_no_wifi;
    private RelativeLayout rel_content;
    public LinearLayout linear_public;
    // 内容区域的布局
    private View contentView;


    private ImageView img_center;

    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cool_activity_base);
        App.getInstance().addActivity(this);
        linear_public = (LinearLayout) findViewById(R.id.id_linear_include);
        ly_content = (LinearLayout) findViewById(R.id.id_linear_content);
        rel_content = (RelativeLayout) findViewById(R.id.rel_content);
        linear_no_wifi = (LinearLayout) findViewById(R.id.linear_no_network);
        mTopLeft = (ImageView) findViewById(R.id.top_left_btn);
        mTopRight = (ImageView) findViewById(R.id.top_right_btn);
        mTopTitle = (TextView) findViewById(R.id.top_title);
        tv_right = (TextView) findViewById(R.id.tv_right);
        img_center = (ImageView) findViewById(R.id.img_center);
        img_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCenter(v);
            }
        });
        mTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLeft(v);
            }
        });
        mTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRight(v);
            }
        });
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRightClick(view);
            }
        });

        EventBus.getDefault().register(CoolBaseActivity.this);

        CoolStatusBarUtil.setColor(CoolBaseActivity.this, getResources().getColor(R.color.black));
//        //状态栏透明和间距处理
//        CoolStatusBar ultimateBar = new CoolStatusBar(this);
//        ultimateBar.setColorBar(getResources().getColor(R.color.common_backgroud), 120);
    }

    @Override
    protected ViewModel initViewModel() {
        return null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.fontScale != 1) { //fontScale不为1，需要强制设置为1
            getResources();
        }
    }

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        if (resources.getConfiguration().fontScale != 1) { //fontScale不为1，需要强制设置为1
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置成默认值，即fontScale为1
            resources.updateConfiguration(newConfig, resources.getDisplayMetrics());
        }
        return resources;
    }

    @Subscribe
    public void onEventMainThread(BaseVO temp) {

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    @Subscribe
    public void onEventMainThread(NetWorkVO temp) {
        if (temp.isConnected()) {
            if (rel_content.getVisibility() == View.GONE) {
                rel_content.setVisibility(View.VISIBLE);
                linear_no_wifi.setVisibility(View.GONE);
                onRefreshContentView();
            }
        } else {
            if (linear_no_wifi.getVisibility() == View.GONE) {
                rel_content.setVisibility(View.GONE);
                linear_no_wifi.setVisibility(View.VISIBLE);
            }
        }
    }


    /***
     * 设置内容区域
     *
     * @param resId 资源文件ID
     */
    public void setContentLayout(int resId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(resId, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(layoutParams);
        contentView.setBackgroundDrawable(null);
        if (null != ly_content) {
            ly_content.addView(contentView);
        }

    }

    /***
     * 设置内容区域
     *
     * @param resId 资源文件ID
     */
    public void setContentLayoutWithoutOrginalTitle(int resId) {
        linear_public.setVisibility(View.GONE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(resId, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(layoutParams);
        contentView.setBackgroundDrawable(null);
        if (null != ly_content) {
            ly_content.addView(contentView);
        }

    }


    /***
     * 设置内容区域
     *
     * @param view View对象
     */
    public void setContentLayout(View view) {
        if (null != ly_content) {
            ly_content.addView(view);
        }
    }

    /**
     * 得到内容的View
     *
     * @return
     */
    public View getLyContentView() {

        return contentView;
    }

    public void setmTopTitle(String txt) {
        mTopTitle.setText(txt);
    }

    public void setTvRight(String txt) {
        tv_right.setText(txt);
    }

    public void setmTopRightImage(int resit) {
        mTopRight.setImageResource(resit);
    }

    protected void onClickRight(View v) {

    }

    protected void onClickCenter(View v) {
    }

    protected void onRightClick(View v) {

    }

    protected void onRefreshContentView() {
        CoolLogTrace.i(CoolBaseActivity.class.getSimpleName(), "onRefreshContentView", "刷新啦");
    }

    protected void onClickLeft(View v) {
//        finish();
    }

    public void setmTopLeftImage(int resId) {
        mTopLeft.setImageResource(resId);
    }


    public void setmTopTitleBackground(int resId) {
        mTopTitle.setBackgroundResource(resId);
    }


    public void setImageCenterVivible(int key) {
        if (key == 0) {
            img_center.setVisibility(View.GONE);
        } else {
            img_center.setVisibility(View.VISIBLE);
        }
    }


    public void setmTopRightVisible(int key) {
        if (key == 0) {
            mTopRight.setVisibility(View.INVISIBLE);
        } else {
            mTopRight.setVisibility(View.VISIBLE);
        }
    }

    public void setmTvRightVisible(int key) {
        if (key == 0) {
            tv_right.setVisibility(View.INVISIBLE);
        } else {
            tv_right.setVisibility(View.VISIBLE);
        }
    }

    public void setmTopLeftVisible(int key) {
        if (key == 0) {
            mTopLeft.setVisibility(View.INVISIBLE);
        } else {
            mTopLeft.setVisibility(View.VISIBLE);
        }
    }

    public void println(String text) {
        System.out.println(text);
    }

    /**
     * 带有右进右出动画的退出
     **/
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.bg_alpha_in, R.anim.bg_alpha_out);
    }

    /**
     * 默认退出
     **/
    protected void defaultFinish() {
        super.finish();
    }

    public <T extends View> T fvbd(int resId) {
        return (T) super.findViewById(resId);
    }


    public <T extends View> T fbc(int id) {
        return (T) super.findViewById(id);
    }

    public <T extends View> T fvbc(View view, int id) {
        return (T) view.findViewById(id);
    }

    /**
     * 启动webView
     *
     * @param title
     * @param url
     */
    public void startCoolWebViewActivity(String title, String url) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("url", url);
        startActivity(CoolWebViewActivity.class, bundle);

    }

    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
        overridePendingTransition(R.anim.bg_alpha_in, R.anim.bg_alpha_out);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            CoolLogTrace.i("tag", "tag", "there is no activity can handle this intent: " + intent.getAction().toString());
        }
        overridePendingTransition(R.anim.bg_alpha_in, R.anim.bg_alpha_out);
    }

    /**
     * 通过Action跳转界面
     **/
    protected void startActivity(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            CoolLogTrace.i("tag", "tag", "there is no activity can handle this intent: " + intent.getAction().toString());
        }
        overridePendingTransition(R.anim.bg_alpha_in, R.anim.bg_alpha_out);

    }

    /**
     * 含有Date通过Action跳转界面
     **/
    protected void startActivity(String action, Uri data) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setData(data);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            CoolLogTrace.i("tag", "tag", "there is no activity can handle this intent: " + intent.getAction().toString());
        }
        overridePendingTransition(R.anim.bg_alpha_in, R.anim.bg_alpha_out);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    protected void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            CoolLogTrace.i("tag", "tag", "there is no activity can handle this intent: " + intent.getAction().toString());
        }
        overridePendingTransition(R.anim.bg_alpha_in, R.anim.bg_alpha_out);
    }

    //    点击edittext以外的区域收起键盘
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            if (isShouldHideInput(v, ev)) {
//
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (imm != null) {
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                }
//            }
//            return super.dispatchTouchEvent(ev);
//        }
//        // 必不可少，否则所有的组件都不会有TouchEvent了
//        if (getWindow().superDispatchTouchEvent(ev)) {
//            return true;
//        }
//        return onTouchEvent(ev);
//    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CoolPublicMethod.isNetworkAvailable(CoolBaseActivity.this)) {
            if (rel_content.getVisibility() == View.GONE) {
                rel_content.setVisibility(View.VISIBLE);
                linear_no_wifi.setVisibility(View.GONE);
            }

        } else {
            if (linear_no_wifi.getVisibility() == View.GONE) {
                rel_content.setVisibility(View.GONE);
                linear_no_wifi.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(CoolBaseActivity.this);
    }


}
