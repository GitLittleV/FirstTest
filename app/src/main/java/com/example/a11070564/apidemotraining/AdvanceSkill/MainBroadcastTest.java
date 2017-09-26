package com.example.a11070564.apidemotraining.AdvanceSkill;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.a11070564.apidemotraining.BroadcastReceiver.ActivityCollector;
import com.example.a11070564.apidemotraining.R;

/**
 * Created by 11070564 on 2017/9/25.
 */

public class MainBroadcastTest extends BascActivity {
    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    public static final String TAG = "MainBroadcastTest";

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainbroadcast);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        Button button = (Button)findViewById(R.id.broadcast_mainbutton);
        People people = (People)getIntent().getParcelableExtra("people_data");
        Toast.makeText(this,people.getName()+"   "+people.getAge(),Toast.LENGTH_SHORT).show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcasttest.FORCE_OFFLINE");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.FORCE_OFFLINE");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);

    }


    class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final  Context context, Intent intent) {

            AlertDialog  dialog=new AlertDialog.Builder(context)
            .setTitle("Warning")
            .setMessage("You OFFLINE")
            .setCancelable(false)//对话框不可取消
             .setPositiveButton("OK", new DialogInterface.OnClickListener() {//设置确定按钮，监听ok键
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     ActivityCollector.finishALL();
                     Intent intent = new Intent(context,LoginActivity.class);
                     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//由于在广播接收器中活动，所以Intent需要加上FLAG_ACTIVITY_NEW_TASK
                     context.startActivity(intent);
                 }
             }).create();
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);//设置对话框类型，使其能在广播接收器中弹出
            dialog.show();
        }
    }

    class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //从系统获取ConnectivityManager实例
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            //从ConnectivityManager获取NetworkInfo实例
            NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
            //通过NetworkInfo的isAvailable方法查看网络状态
            if (networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context,"network is available",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"network 9s unavailable",Toast.LENGTH_SHORT).show();
            }

        }
    }



}
