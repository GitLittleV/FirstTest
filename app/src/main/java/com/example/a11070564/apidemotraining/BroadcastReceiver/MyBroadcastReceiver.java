package com.example.a11070564.apidemotraining.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by 11070564 on 2017/9/25.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"receiver in MyBroadcastReceiver",Toast.LENGTH_LONG).show();
        abortBroadcast();
    }
}
