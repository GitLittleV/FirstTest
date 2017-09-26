package com.example.a11070564.apidemotraining.AdvanceSkill;

import android.app.Application;
import android.content.Context;

/**
 * Created by 11070564 on 2017/9/25.
 */

public class CustomApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();

    }

    public static Context getContext() {
        return context;
    }
    /*
    在AndroidManifest中给<application 添加 name项
     */
}
