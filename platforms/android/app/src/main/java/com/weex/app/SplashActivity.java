package com.weex.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.weex.app.util.UIUtils;


public class SplashActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    UIUtils.postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(SplashActivity.this, WXPageActivity.class);
        Uri data = getIntent().getData();
        if (data != null) {
          intent.setData(data);
        }
        intent.putExtra("from", "splash");
        startActivity(intent);
        finish();
      }
    }, 1500);

//    View textView = findViewById(R.id.fullscreen_content);
//    ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//    RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//
//    AnimationSet animationSet = new AnimationSet(false);
//    animationSet.addAnimation(scaleAnimation);
//    animationSet.addAnimation(rotateAnimation);
//    animationSet.setDuration(1500);
//
//    animationSet.setAnimationListener(new Animation.AnimationListener() {
//      @Override
//      public void onAnimationStart(Animation animation) {
//      }
//
//      @Override
//      public void onAnimationEnd(Animation animation) {
//        Intent intent = new Intent(SplashActivity.this, WXPageActivity.class);
//        Uri data = getIntent().getData();
//        if (data != null) {
//          intent.setData(data);
//        }
//        intent.putExtra("from", "splash");
//        startActivity(intent);
//        finish();
//      }
//
//      @Override
//      public void onAnimationRepeat(Animation animation) {
//      }
//    });
//    textView.startAnimation(animationSet);
  }
}
