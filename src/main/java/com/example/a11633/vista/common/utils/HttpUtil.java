package com.example.a11633.vista.common.utils;

import android.app.Activity;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 11633 on 2018/4/2.
 */


/**
 * 项目中关于http请求的工具类
 * */
public class HttpUtil {
    /**
     * 用于向服务器发送请求
     * @author followWindD
     * @date 2018/4/14 16:53
     * @param address 请求的相应url地址
     * @param callback 回调函数
     * @param pairs 请求所带的参数
     * @return
     * @throws
     * @since
    */
    public static void sendHttpRequest(String address,okhttp3.Callback callback,String... pairs){
        OkHttpClient OkHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        if(pairs.length%2!=0)
            throw new IllegalStateException("pairs的输入应为键值对");
        for (int i = 0; i < pairs.length; i+=2) {
            builder.add(pairs[i],pairs[i+1]);
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(address).post(requestBody).build();
        OkHttpClient.newCall(request).enqueue(callback);//自动创建新线程处理相应,并且在接收到相应的时候进行回调
    }

    /**
     * 用于在非ui线程中Tosat信息
     * @author followWindD
     * @date 2018/4/14 16:55
     * @param activity 用于显示的上下文
     * @param msg 显示的信息
     * @return
     * @throws
     * @since
    */
    public static void toastOnUI(final Activity activity,final String msg){
       activity.runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
                                  }
                              }
       );
    }

}
