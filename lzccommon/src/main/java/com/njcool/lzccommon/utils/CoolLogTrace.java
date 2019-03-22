package com.njcool.lzccommon.utils;

import android.util.Log;

public class CoolLogTrace {

    private static boolean sIsLogEnabled = true;//是否打开LOG

    public static void i(String msg) {
        if (sIsLogEnabled) {
            Log.i("ayapp" + "-->", msg);
        }
    }

    public static void i(String TAG, String methodName, String msg) {
        if (sIsLogEnabled) {
            Log.i(TAG + "-->", methodName + "-->" + msg);
        }
    }

    public static void d(String TAG, String methodName, String msg) {
        if (sIsLogEnabled) {
            Log.d(TAG + "-->", methodName + "-->" + msg);
        }
    }

    public static void w(String TAG, String methodName, String msg) {
        if (sIsLogEnabled) {
            Log.w(TAG + "-->", methodName + "-->" + msg);
        }
    }

    public static void e(String TAG, String methodName, String msg) {
        if (sIsLogEnabled) {
            Log.e(TAG + "-->", methodName + "-->" + msg);
        }
    }

    public static void e(String msg) {
        if (sIsLogEnabled) {
            Log.e("aiapp" + "-->", msg);
        }
    }

    public static void v(String TAG, String methodName, String msg) {
        if (sIsLogEnabled) {
            Log.v(TAG + "-->", methodName + "-->" + msg);
        }
    }
}
