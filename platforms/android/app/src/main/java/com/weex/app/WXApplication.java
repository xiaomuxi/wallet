package com.weex.app;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.alibaba.weex.plugin.loader.WeexPluginContainer;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.weex.app.components.QRCodeImage;
import com.weex.app.extend.ImageAdapter;
import com.weex.app.extend.WXEventModule;
import com.weex.app.module.UIModule;
import com.weex.app.util.AppConfig;
import com.weex.app.util.UIUtils;

public class WXApplication extends Application {

  /**
   * 全局Context，原理是因为Application类是应用最先运行的，所以在我们的代码调用时，该值已经被赋值过了
   */
  private static WXApplication mInstance;
  /**
   * 主线程ID
   */
  private static int mMainThreadId = -1;
  /**
   * 主线程ID
   */
  private static Thread mMainThread;
  /**
   * 主线程Handler
   */
  private static Handler mMainThreadHandler;
  /**
   * 主线程Looper
   */
  private static Looper mMainLooper;

  private static int statusBarHeight = 0;

  @Override
  public void onCreate() {
    super.onCreate();
    //android.os.Process.myTid()  获取调用进程的id
    //android.os.Process.myUid() 获取 该进程的用户id
    //android.os.Process.myPid() 获取进程的id
    mMainThreadId = android.os.Process.myTid();
    mMainThread = Thread.currentThread();
    mMainThreadHandler = new Handler();
    mMainLooper = getMainLooper();
    mInstance = this;

    statusBarHeight = UIUtils.getStatusBarHeight();

    WXSDKEngine.addCustomOptions("appName", "WXSample");
    WXSDKEngine.addCustomOptions("appGroup", "WXApp");
    WXSDKEngine.initialize(this,
        new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build()
    );
    try {
      WXSDKEngine.registerModule("event", WXEventModule.class);
      WXSDKEngine.registerModule("UIManager", UIModule.class);
      WXSDKEngine.registerComponent("qr-image", QRCodeImage.class);
    } catch (WXException e) {
      e.printStackTrace();
    }
    AppConfig.init(this);
    WeexPluginContainer.loadAll(this);
  }

  public static WXApplication getApplication() {
    return mInstance;
  }

  /**
   * 获取主线程ID
   */
  public static int getMainThreadId() {
    return mMainThreadId;
  }

  /**
   * 获取主线程
   */
  public static Thread getMainThread() {
    return mMainThread;
  }

  /**
   * 获取主线程的handler
   */
  public static Handler getMainThreadHandler() {
    return mMainThreadHandler;
  }

  /**
   * 获取主线程的looper
   */
  public static Looper getMainThreadLooper() {
    return mMainLooper;
  }

  /**
   * Status bar height
   */
  public static int getStatusBarHeight() {
    return statusBarHeight;
  }
}
