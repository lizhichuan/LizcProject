package com.njcool.lzccommon.log.system;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;

import com.njcool.lzccommon.log.ViseLog;


/**
 * <!-- 解锁 -->
 * require <uses-permission android:name="android.permission.DISABLE_KEYGUARD"/>
 */
public class KeyguardLock {
    KeyguardManager keyguardManager;
    KeyguardManager.KeyguardLock keyguardLock;

    public KeyguardLock(Context context, String tag) {
        //获取系统服务
        keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        //初始化键盘锁，可以锁定或解开键盘锁
        keyguardLock = keyguardManager.newKeyguardLock(tag);
    }

    /**
     * Call requires API level 16
     */
    public boolean isKeyguardLocked() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            ViseLog.e("can not call isKeyguardLocked if SDK_INT < 16 ");
            return false;
        } else {
            return keyguardManager.isKeyguardLocked();
        }

    }

    /**
     * Call requires API level 16
     */
    public boolean isKeyguardSecure() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            ViseLog.e("can not call isKeyguardSecure if SDK_INT < 16 ");
            return false;
        } else {
            return keyguardManager.isKeyguardSecure();
        }
    }

    public boolean inKeyguardRestrictedInputMode() {
        return keyguardManager.inKeyguardRestrictedInputMode();
    }

    public void disableKeyguard() {
        keyguardLock.disableKeyguard();
    }

    public void reenableKeyguard() {
        keyguardLock.reenableKeyguard();
    }

    public void release() {
        if (keyguardLock != null) {
            //禁用显示键盘锁定
            keyguardLock.reenableKeyguard();
        }
    }

    public KeyguardManager getKeyguardManager() {
        return keyguardManager;
    }

    public void setKeyguardManager(KeyguardManager keyguardManager) {
        this.keyguardManager = keyguardManager;
    }

    public KeyguardManager.KeyguardLock getKeyguardLock() {
        return keyguardLock;
    }

    public void setKeyguardLock(KeyguardManager.KeyguardLock keyguardLock) {
        this.keyguardLock = keyguardLock;
    }
}
