package com.example.a11070564.apidemotraining;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by 11070564 on 2017/9/16.
 */

public class TitleLayout extends LinearLayout implements Button.OnClickListener{
    //重写了LinearLayout中两个参数的构造函数，在布局引入TitleLayout时就会调用
    public TitleLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        //LayoutInflater的From构造出一个LayoutInflater对象调用inflate（）方法动态加载一个布局文件（布局文件，父布局）
        LayoutInflater.from(context).inflate(R.layout.layout_custom_title,this);
        Button button_edit = (Button)findViewById(R.id.title_edit);
        Button button_back = (Button)findViewById(R.id.title_back);

        button_back.setOnClickListener(this);
        button_edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "no Function", Toast.LENGTH_SHORT).show();
            }
        });

        }

            @Override
            public void onClick(View v) {
                ((Activity )getContext()).finish();
            }

}
