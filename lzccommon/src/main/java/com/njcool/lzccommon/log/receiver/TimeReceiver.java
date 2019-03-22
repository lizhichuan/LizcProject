package com.njcool.lzccommon.log.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.njcool.lzccommon.log.ViseLog;


/**
 * 时间广播
 */
public class TimeReceiver extends BroadcastReceiver {

    private TimeListener timeListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        ViseLog.i("action: " + intent.getAction());
        ViseLog.d("intent : ");
        Bundle bundle = intent.getExtras();
        for (String key : bundle.keySet()) {
            ViseLog.d(key + " : " + bundle.get(key));
        }
        if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
            if (timeListener != null) {
                timeListener.onTimeTick();
            }
        } else if (Intent.ACTION_TIME_CHANGED.equals(intent.getAction())) {
            if (timeListener != null) {
                timeListener.onTimeChanged();
            }
        } else if (Intent.ACTION_TIMEZONE_CHANGED.equals(intent.getAction())) {
            if (timeListener != null) {
                timeListener.onTimeZoneChanged();
            }
        }
    }

    public void registerReceiver(Context context, TimeListener timeListener) {
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_TIME_CHANGED);
            filter.addAction(Intent.ACTION_TIME_TICK);
            filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
            filter.setPriority(Integer.MAX_VALUE);
            context.registerReceiver(this, filter);
            this.timeListener = timeListener;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unRegisterReceiver(Context context) {
        try {
            context.unregisterReceiver(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static interface TimeListener {
        /**
         * 时区改变
         */
        public void onTimeZoneChanged();

        /**
         * 设置时间
         */
        public void onTimeChanged();

        /**
         * 每分钟调用
         */
        public void onTimeTick();
    }
}
