package com.example.a11070564.apidemotraining.Notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.a11070564.apidemotraining.R;

/**
 * Created by 11070564 on 2017/9/19.
 */

public class Notification_Activity extends Activity implements View.OnClickListener {
    private Button sendNotice;
    private Button sendNotice2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        sendNotice = (Button)findViewById(R.id.send_noice);
        sendNotice2 = (Button)findViewById(R.id.send_noice2);
        sendNotice.setOnClickListener(this);
        sendNotice2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_noice:
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                Notification.Builder builder = new Notification.Builder(Notification_Activity.this);
                builder.setSmallIcon(R.drawable.photo6);
                builder.setTicker(getResources().getString(R.string.ticker_notice));
                builder.setContentTitle(getResources().getString(R.string.title_notice));
                builder.setContentText(getResources().getString(R.string.content_notice));;
                builder.setWhen(System.currentTimeMillis());
                builder.setDefaults(Notification.DEFAULT_ALL);

                Intent intent1 = new Intent(this,Notification_rep.class);

                PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                        new Intent(this, Notification_rep.class), 0);

                builder.setContentIntent(contentIntent);
                Notification notification= builder.build();

                 manager.notify(1,notification);
                break;
            case R.id.send_noice2:
                NotificationManager manager2 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                Notification.Builder builder2 = new Notification.Builder(Notification_Activity.this);
                builder2.setSmallIcon(R.drawable.photo6);
                builder2.setTicker(getResources().getString(R.string.ticker_notice));
                builder2.setContentTitle(getResources().getString(R.string.title_notice));
                builder2.setContentText(getResources().getString(R.string.content_notice));;
                builder2.setWhen(System.currentTimeMillis());
                builder2.setDefaults(Notification.DEFAULT_ALL);
                Notification notification2= builder2.build();
                manager2.notify(2,notification2);

            default:
                break;
        }
    }
}
