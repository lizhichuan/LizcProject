package com.njcool.lzccommon.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.njcool.lzccommon.R;
import com.njcool.lzccommon.activity.CoolBaseActivity;
import com.njcool.lzccommon.qrcode.core.QRCodeView;
import com.njcool.lzccommon.qrcode.zxing.ZXingView;

public class ScanQrcodeActivity extends CoolBaseActivity implements QRCodeView.Delegate {


    private static final String TAG = ScanQrcodeActivity.class.getSimpleName();
    private ZXingView zxingview;
    private CheckBox cbFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_scan_qrcode);
        findViews();
    }

    @Override
    protected void onClickLeft(View v) {
        finish();
        super.onClickLeft(v);
    }

    private void findViews() {
        setmTopTitle("二维码扫描");
        zxingview = (ZXingView) findViewById(R.id.zxingview);
        cbFlash = (CheckBox) findViewById(R.id.cb_flash);
        zxingview.setDelegate(this);
        zxingview.startSpotAndShowRect();
        cbFlash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbFlash.setText("关闭闪光灯");
                    zxingview.openFlashlight();
                } else {
                    cbFlash.setText("打开闪光灯");
                    zxingview.closeFlashlight();
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        zxingview.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
        zxingview.startSpotAndShowRect();
    }

    @Override
    protected void onStop() {
        zxingview.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zxingview.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        try {
            vibrator.vibrate(200);
        } catch (Exception e) {

        }
    }


    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
//        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();
        zxingview.startSpotAndShowRect();
        Intent data = new Intent();
        data.putExtra(QrcodeConstant.EXTRA_RESULT_CONTENT, result);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }
}
