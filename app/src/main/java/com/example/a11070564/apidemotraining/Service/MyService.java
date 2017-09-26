package com.example.a11070564.apidemotraining.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.a11070564.apidemotraining.Notification.Notification_Activity;
import com.example.a11070564.apidemotraining.R;

import static android.content.ContentValues.TAG;

/**
 * Created by 11070564 on 2017/9/21.
 */

public class MyService extends Service {

    public static final String TAG="myservice";
    private  DownloaderBinder mBinder = new DownloaderBinder();

    class  DownloaderBinder extends Binder{

        public  void startDownload(){
            Log.d(TAG, "start Download");
        }
        public int getProgress(){
            Log.d(TAG, "get Progress");
            return 0;
        }

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent  =new Intent(this,Service_Activity.class);
        PendingIntent pending = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new Notification.Builder(MyService.this)
                .setSmallIcon(R.drawable.photo6)
                .setTicker(getResources().getString(R.string.ticker_notice))
                .setWhen(System.currentTimeMillis())
                .setContentText(getResources().getString(R.string.content_notice))
                .setContentTitle(getResources().getString(R.string.title_notice))
                .setContentIntent(pending)
                .build();
           // manager.notify(1,notification);
        startForeground(1,notification);


        Log.d(TAG,"onCreat executed");
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {

        new Thread(new Runnable(){
            @Override
            public void run() {
                Log.d(TAG, "Running Thread");
                stopSelf();
            }
        }).start();
        Log.d(TAG,"onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        //stopForeground(true);
        Log.d(TAG,"onDestroy executed");
        super.onDestroy();
    }

}
