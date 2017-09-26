package com.example.a11070564.apidemotraining.Notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.a11070564.apidemotraining.R;

/**
 * Created by 11070564 on 2017/9/20.
 */

public class Notification_rep extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        //在新的activity中记得取消消息
        NotificationManager manager =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);
        manager.cancel(2);
    }
}
