package com.example.a11070564.apidemotraining;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
/**
 * Created by 11070564 on 2017/9/18.
 */



/**
 * Created by HPZ on 2017/9/18.
 */
public class ServiceList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);

        ActivityManager activityManger = (ActivityManager) getSystemService(ACTIVITY_SERVICE);// 获取Activity管理器
        List<ActivityManager.RunningServiceInfo> serviceList = activityManger.getRunningServices(30);// 从窗口管理器中获取正在运行的Service
        tv.setText(getServicesName(serviceList));



        setContentView(tv);

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView tv2 = new TextView(this);
        ActivityManager activityManger = (ActivityManager) getSystemService(ACTIVITY_SERVICE);// 获取Activity管理器
        List<ActivityManager.RunningAppProcessInfo> activtiyList = activityManger.getRunningAppProcesses();// 从窗口管理器中获取正在运行的processs
        for (ActivityManager.RunningAppProcessInfo running : activtiyList){
            tv2.setText(running.processName);
        }
        tv2.setTextColor(getResources().getColor(R.color.colorPrimary));

        setContentView(tv2);
    }

    private boolean ServiceIsStart(List<ActivityManager.RunningServiceInfo> list, String className) {// 判断某个服务是否启动
        for (int i = 0; i < list.size(); i++) {
            if (className.equals(list.get(i).service.getClassName()))
                return true;
        }
        return false;
    }

    private String getServicesName(List<ActivityManager.RunningServiceInfo> list) {// 获取所有服务的名称
        String res = "";
        for (int i = 0; i < list.size(); i++) {
            res += list.get(i).service.getClassName() + "/n";
        }
        return res;
    }
}