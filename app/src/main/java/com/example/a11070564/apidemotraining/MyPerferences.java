package com.example.a11070564.apidemotraining;

import android.app.Activity;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 11070564 on 2017/9/26.
 */

public class MyPerferences extends Activity {

    Button saveData;
    Button getData;

    public static final String TAG="MyPerferences";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perferences);
        saveData=(Button)findViewById(R.id.perferences_save);
        getData=(Button)findViewById(R.id.perferences_read);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MyPerferences.this);
                editor.putString("name","Juee");
                editor.putInt("age",20);
                editor.putBoolean("married",false);
                editor.commit();
                Toast.makeText(MyPerferences.this,"COMMIT",Toast.LENGTH_SHORT).show();
            }
        });
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
                String name = preferences.getString("name","");
                int age = preferences.getInt("age",0);
                boolean married = preferences.getBoolean("married",false);

                Log.d(TAG, "name is "+name);
                Log.d(TAG, "age is "+age);
                Log.d(TAG, "married is "+married);
                Toast.makeText(MyPerferences.this,"READ!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
