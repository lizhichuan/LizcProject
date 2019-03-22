package com.njcool.lzccommon.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.njcool.lzccommon.R;
import com.njcool.lzccommon.app.App;
import com.njcool.lzccommon.utils.CoolLogTrace;
import com.njcool.lzccommon.utils.CoolPublicMethod;
import com.njcool.lzccommon.view.swprefresh.CoolSwipeRefreshLayout;

import static com.njcool.lzccommon.R.id.swp;


/**
 * Cool共用webview
 */
public class CoolWebViewActivity extends CoolBaseActivity {

    private WebView webView;
    private CoolSwipeRefreshLayout swipeRefreshLayout;
    private String url = "";
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.cool_activity_web_view);
        App.getInstance().addActivity(this);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        CoolLogTrace.i("coolWebViewActivity", "coolWebViewActivity", "url=" + url);
        findViews();
    }


    public void initTopViews() {
        setmTopTitle(title);
        setmTopLeftVisible(1);
    }

    @Override
    protected void onClickLeft(View v) {
        super.onClickLeft(v);
        finish();
    }

    @Override
    protected void onClickRight(View v) {
        super.onClickRight(v);
//        startActivity(new Intent(CoolWebViewActivity.this, CoolShareActivity.class)
//                .putExtra("url", url).putExtra("content", title));
//        overridePendingTransition(R.anim.bg_alpha_in, R.anim.bg_alpha_out);
    }


    public void findViews() {
        initTopViews();
        if (TextUtils.isEmpty(url)) {
            url = "http://www.artmkt.com";
        }
        if (!url.startsWith("http")) {
            url = "http://" + url;
        }
        webView = (WebView) findViewById(R.id.id_webview);
        swipeRefreshLayout = (CoolSwipeRefreshLayout) findViewById(swp);
        CoolPublicMethod.setWebView(CoolWebViewActivity.this, webView);
        // 设置进度条
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                // Log.e(TAG, "progress = " + newProgress);
                if (newProgress == 100) {
                    // 隐藏进度条
                    swipeRefreshLayout.setRefreshing(false);
                    CoolPublicMethod.hideProgressDialog();
                } else {
//                    if (!swipeRefreshLayout.isRefreshing())
//                        swipeRefreshLayout.finishRefresh();
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setmTopTitle(title);
            }
        });

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.gray));
        swipeRefreshLayout.setRefreshStyle(CoolSwipeRefreshLayout.STYLE_SMARTISAN);
        swipeRefreshLayout.setOnRefreshListener(new CoolSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl(url);
                    }
                });
            }
        });
        // 设置子视图是否允许滚动到顶部
//        swipeRefreshLayout.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {
//            @Override
//            public boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View child) {
//                return webView.getScrollY() > 0;
//            }
//        });

        webView.loadUrl(url);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.removeAllViews();
        webView.destroy();
    }

//    //改写物理按键——返回的逻辑
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (webView.canGoBack()) {
//                webView.goBack();//返回上一页面
//                return true;
//            } else {
//                System.exit(0);//退出程序
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
