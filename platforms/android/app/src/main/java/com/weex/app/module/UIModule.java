package com.weex.app.module;

import android.app.Activity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.weex.app.ActivityCache;
import com.weex.app.CustomScannerActivity;
import com.weex.app.WXApplication;

public class UIModule extends WXModule {

    //run JS thread
    @JSMethod(uiThread = false)
    public void getStatusBarHeight(JSCallback callback) {

        int statusBarHeight = WXApplication.getStatusBarHeight();
        callback.invoke(statusBarHeight);
    }

    @JSMethod
    public void closeApp() {
        ActivityCache.finishActivity();
    }

    @JSMethod
    public void scan() {
        Activity activity = ActivityCache.activityList.get(0);
        if (activity != null) {
            IntentIntegrator integrator = new IntentIntegrator(activity);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            integrator.setCaptureActivity(CustomScannerActivity.class);
            integrator.setPrompt("请将镜头对准二维码进行扫描");
            //integrator.setCameraId(0);  // Use a specific camera of the device
            integrator.setBeepEnabled(true);
            integrator.setOrientationLocked(false);
            integrator.setBarcodeImageEnabled(true);
//            integrator.setPrompt(activity.getString(R.string.capture_qrcode_prompt));
            integrator.initiateScan();
        }
    }
}
