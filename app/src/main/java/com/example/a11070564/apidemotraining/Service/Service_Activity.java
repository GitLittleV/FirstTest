package com.example.a11070564.apidemotraining.Service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a11070564.apidemotraining.R;


/**
 * Created by 11070564 on 2017/9/20.
 */

public class Service_Activity extends Activity implements View.OnClickListener {

    public static final int UPDATE_TEXT = 1;
    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private  MyService.DownloaderBinder downloaderBinder;

    //创建匿名类。重写onServiceConnected在活动与服务绑定是调用。onServiceDisconnected在活动与服务解除绑定时调用。
    //onServiceConnected通过向下转型得到DownloaderBinder实例。由此可以在具体场景中调用DownloaderBinder的任何public方法。如startDownload，getProgress
    //
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name , IBinder service) {
            downloaderBinder = (MyService.DownloaderBinder) service ;
            downloaderBinder.startDownload();
            downloaderBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        startService=(Button)findViewById(R.id.start_service);
        stopService=(Button)findViewById(R.id.stop_service);
       startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService=(Button)findViewById(R.id.bind_service);
        unbindService=(Button)findViewById(R.id.unbind_service);
       bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent = new Intent(this,MyService.class);
                startService(startIntent);
                break;
            case  R.id.stop_service:
                Intent stopIntent = new Intent(this,MyService.class);
                stopService(stopIntent);
            //点击启动
            case R.id.bind_service:

                Intent bindIntent = new Intent(this,MyService.class);

                //参数  Intent ServiceConnection  活动和服务进程绑定后自动创建服务——使MyService方法中onCreate方法执行而onStratCommand不执行
                bindService(bindIntent,connection,BIND_AUTO_CREATE);

                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            default:
                break;
        }
    }
}
