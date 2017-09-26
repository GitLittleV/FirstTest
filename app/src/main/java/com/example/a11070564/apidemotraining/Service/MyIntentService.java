package com.example.a11070564.apidemotraining.Service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 11070564 on 2017/9/21.
 */

public class MyIntentService extends IntentService {
    public static final String TAG = "MyIntentService";
    public  MyIntentService(){
        super("MyIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: ");

        AlarmManager manager= (AlarmManager)getSystemService(ALARM_SERVICE);
        int anHour = 60*60*1000;//一小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime()
                +anHour;
       // long Time = SystemClock.currentThreadTimeMillis()



       /* AlarmManager.ELAPSED_REALTIME;
        AlarmManager.ELAPSED_REALTIME_WAKEUP;
        AlarmManager.RTC;
        AlarmManager.RTC_WAKEUP;*/


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

}
