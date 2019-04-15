/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.weex.app.extend;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;
import com.weex.app.R;

import java.lang.reflect.Field;

public class ImageAdapter implements IWXImgLoaderAdapter {

  public ImageAdapter() {
  }

  @Override
  public void setImage(final String url, final ImageView view,
                       WXImageQuality quality, final WXImageStrategy strategy) {

    WXSDKManager.getInstance().postOnUiThread(new Runnable() {

      @Override
      public void run() {
        if(view==null||view.getLayoutParams()==null){
          return;
        }
        if (TextUtils.isEmpty(url)) {
          view.setImageBitmap(null);
          return;
        }
        String temp = url;
        int imgSource = -1;
        if (url.startsWith("//")) {
          temp = "http:" + url;
        }
        if (url.startsWith("local:///")) {
          try {
            imgSource = getResourceByReflect(temp);
            if (imgSource != -1) {
              temp = null;
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        if (view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
          return;
        }


        if(!TextUtils.isEmpty(strategy.placeHolder)){
          Picasso.Builder builder=new Picasso.Builder(WXEnvironment.getApplication());
          Picasso picasso=builder.build();
          picasso.load(Uri.parse(strategy.placeHolder)).into(view);

          view.setTag(strategy.placeHolder.hashCode(),picasso);
        }

        Picasso picasso = Picasso.with(WXEnvironment.getApplication());
        RequestCreator requestCreator;
        if (temp == null) {
          requestCreator = picasso.load(imgSource);
        }
        else {
          requestCreator = picasso.load(temp);
        }
        requestCreator.transform(new BlurTransformation(strategy.blurRadius))
            .into(view, new Callback() {
              @Override
              public void onSuccess() {
                if(strategy.getImageListener()!=null){
                  strategy.getImageListener().onImageFinish(url,view,true,null);
                }

                if(!TextUtils.isEmpty(strategy.placeHolder)){
                  ((Picasso) view.getTag(strategy.placeHolder.hashCode())).cancelRequest(view);
                }
              }

              @Override
              public void onError() {
                if(strategy.getImageListener()!=null){
                  strategy.getImageListener().onImageFinish(url,view,false,null);
                }
              }
            });
      }
    },0);
  }

  /**
   * 获取图片名称获取图片的资源id的方法
   * @param imageName
   * @return
   */
  public int getResourceByReflect(String imageName){
    Class drawable  =  R.drawable.class;
    Field field = null;
    int r_id = -1;
    try {
      field = drawable.getField(imageName);
      r_id = field.getInt(field.getName());
    } catch (Exception e) {
//      r_id=R.drawable.b_nothing;
      Log.e("ERROR", "PICTURE NOT　FOUND！");
    }
    return r_id;
  }
}
