package com.example.a11070564.apidemotraining.BroadcastReceiver;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11070564 on 2017/9/25.
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);

    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static  void finishALL(){
        for (Activity activity:activities)
        {
            activity.finish();
        }
    }
}
