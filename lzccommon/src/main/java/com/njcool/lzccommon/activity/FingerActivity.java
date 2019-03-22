package com.njcool.lzccommon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.njcool.lzccommon.R;
import com.njcool.lzccommon.fingercore.FingerprintIdentify;
import com.njcool.lzccommon.fingercore.base.BaseFingerprint;
import com.njcool.lzccommon.utils.CoolPublicMethod;


public class FingerActivity extends CoolBaseActivity {


    TextView tryText;
    ImageView imageFinger;
    TextView tvCancel;


    private FingerprintIdentify mFingerprintIdentify;

    private static final int MAX_AVAILABLE_TIMES = 5;
    //    private FingerprintCore mFingerprintCore;
    public static final int FINGER_SET = 0, FINGER_VERFITY = 1;
    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger);
        findViews();
    }

    private void findViews() {
        tryText = (TextView) findViewById(R.id.try_text);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        imageFinger = (ImageView) findViewById(R.id.image_finger);
        tryText.setText("指纹识别已经开启，长按指纹解锁键");

        mFingerprintIdentify = new FingerprintIdentify(getApplicationContext(),
                new BaseFingerprint.FingerprintIdentifyExceptionListener() {
                    @Override
                    public void onCatchException(Throwable exception) {
                        System.out.println("\nException：" + exception.getLocalizedMessage());
                    }

                });

        if (!mFingerprintIdentify.isFingerprintEnable()) {
            CoolPublicMethod.Toast(FingerActivity.this, getString(R.string.fingerprint_recognition_not_support));
            tryText.setText(getString(R.string.fingerprint_recognition_not_support));
            return;
        } else {
            mHandler.removeMessages(999);
            mHandler.sendEmptyMessageDelayed(999, 1000);
        }


        imageFinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVertify();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("result", false);
                i.putExtra("pass", false);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }


    private void startVertify() {
        if (mFingerprintIdentify == null) {
            mFingerprintIdentify = new FingerprintIdentify(getApplicationContext(), new BaseFingerprint.FingerprintIdentifyExceptionListener() {
                @Override
                public void onCatchException(Throwable exception) {
                    System.out.println("\nException：" + exception.getLocalizedMessage());
                }
            });
        }
        mFingerprintIdentify.startIdentify(MAX_AVAILABLE_TIMES, new BaseFingerprint.FingerprintIdentifyListener() {
            @Override
            public void onSucceed() {
                CoolPublicMethod.Toast(FingerActivity.this, "识别成功");
                Intent i = new Intent();
                i.putExtra("result", true);
                i.putExtra("pass", false);
                setResult(RESULT_OK, i);
                finish();
            }

            @Override
            public void onNotMatch(int availableTimes) {
                if (tryText != null) {
                    tryText.setText("指纹不匹配，还可以尝试" + availableTimes + "次");
                }
            }

            @Override
            public void onFailed(boolean isDeviceLocked) {
                System.out.println("\n" + " 验证失败，结束，是设备锁定" + isDeviceLocked);
                if (type == FINGER_VERFITY) {
                    CoolPublicMethod.Toast(FingerActivity.this, "识别失败次数超5次，请使用支付密码验证");
                    Intent i = new Intent();
                    i.putExtra("pass", true);
                    i.putExtra("result", false);
                    setResult(RESULT_OK, i);
                    finish();
                } else {
                    if (tryText != null) {
                        tryText.setText("1分钟后可重试!");
                    }
                    mHandler.removeMessages(999);
                    mHandler.sendEmptyMessageDelayed(999, 1000 * 60);
                }
            }

            @Override
            public void onStartFailedByDeviceLocked() {
                System.out.println("\n" + "开始失败，设备暂时锁定");
                if (type == FINGER_VERFITY) {
                    CoolPublicMethod.Toast(FingerActivity.this, "识别失败次数超5次，请使用支付密码验证");
                    Intent i = new Intent();
                    i.putExtra("pass", true);
                    i.putExtra("result", false);
                    setResult(RESULT_OK, i);
                    finish();
                } else {
                    if (tryText != null) {
                        tryText.setText("1分钟后可重试!");
                    }
                    mHandler.removeMessages(999);
                    mHandler.sendEmptyMessageDelayed(999, 1000 * 60);
                }

            }
        });
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 999) {
                if (tryText != null) {
                    tryText.setText("指纹识别已经开启，长按指纹解锁键");
                }
                startVertify();
            }

        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        mFingerprintIdentify.resumeIdentify();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFingerprintIdentify.cancelIdentify();
    }

    @Override
    protected void onDestroy() {
        mHandler.removeMessages(999);
        mFingerprintIdentify.cancelIdentify();
        super.onDestroy();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // TODO Auto-generated method stub
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            Intent i = new Intent();
            i.putExtra("result", false);
            i.putExtra("pass", false);
            setResult(RESULT_OK, i);
            finish();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

}
