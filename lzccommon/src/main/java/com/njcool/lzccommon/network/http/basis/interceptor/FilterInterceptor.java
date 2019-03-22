package com.njcool.lzccommon.network.http.basis.interceptor;

import android.support.annotation.NonNull;

import com.njcool.lzccommon.app.App;
import com.njcool.lzccommon.utils.CoolDateUtil;
import com.njcool.lzccommon.utils.CoolMD5;
import com.njcool.lzccommon.utils.CoolPublicMethod;
import com.njcool.lzccommon.utils.CoolSPUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：leavesC
 * 时间：2018/10/28 9:31
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
public class FilterInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
//        HttpUrl.Builder httpBuilder = originalRequest.url().newBuilder();
//        Headers headers = originalRequest.headers();
//        if (headers != null && headers.size() > 0) {
//            String requestType = headers.get(HttpConfig.HTTP_REQUEST_TYPE_KEY);
//            if (!TextUtils.isEmpty(requestType)) {
//                switch (requestType) {
//                    case HttpConfig.HTTP_REQUEST_WEATHER: {
//                        httpBuilder.addQueryParameter(HttpConfig.KEY, HttpConfig.KEY_WEATHER);
//                        break;
//                    }
//                    case HttpConfig.HTTP_REQUEST_QR_CODE: {
//                        httpBuilder.addQueryParameter(HttpConfig.KEY, HttpConfig.KEY_QR_CODE);
//                        break;
//                    }
//                    case HttpConfig.HTTP_REQUEST_NEWS: {
//                        httpBuilder.addQueryParameter(HttpConfig.KEY, HttpConfig.KEY_NEWS);
//                        break;
//                    }
//                }
//            }
//        }
//        Request.Builder requestBuilder = originalRequest.newBuilder()
//                .removeHeader(HttpConfig.HTTP_REQUEST_TYPE_KEY)
//                .url(httpBuilder.build());
        long time = System.currentTimeMillis();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .header("appId", "aiyi")
                .header("timestamp", CoolDateUtil.formateMillisToYYYYMMDDHHmm(time))
                .header("os", "android")
                .header("version", CoolPublicMethod.getVersionName(App.getInstance()))
                .header("token", CoolSPUtil.getDataFromLoacl(App.getInstance(), "token"))
                .header("sign", getSign(CoolDateUtil.formateMillisToYYYYMMDDHHmm(time),
                        CoolSPUtil.getDataFromLoacl(App.getInstance(), "token")))
                .header("UserAgent", "Aiyi/" + CoolPublicMethod.getVersionName(App.getInstance()));
        Request request = requestBuilder.build();


        return chain.proceed(request);
    }

    /**
     * 签名
     *
     * @param time
     * @param token
     * @return
     */
    public static String getSign(String time, String token) {
        StringBuffer sb = new StringBuffer();
        sb.append("aiyi").append(time).append(token).append("DQNLKRFQTKXPCRTXFIYG");
        CoolMD5 coolMD5 = new CoolMD5();
        return coolMD5.getMD5ofStr(sb.toString()).toLowerCase();
    }
}
