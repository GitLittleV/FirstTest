package com.example.a11070564.apidemotraining.AdvanceSkill;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.a11070564.apidemotraining.BroadcastReceiver.ActivityCollector;

/**
 * Created by 11070564 on 2017/9/25.
 */

public class BascActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
