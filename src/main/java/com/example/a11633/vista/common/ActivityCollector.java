package com.example.a11633.vista.common;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11633 on 2018/4/2.
 * 一个管理当前引用所有的活动的容器
 */

public class ActivityCollector {
    private static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    /**
     * 用于一键退出应用
     * @author followWindD
     * @date 2018/4/14 17:02
     * @param
     * @return
     * @throws
     * @since
    */
    public static void finish(){
        for (Activity activity : activities) {
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
