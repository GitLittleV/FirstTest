package com.example.a11070564.apidemotraining.AdvanceSkill;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.a11070564.apidemotraining.R;

/**
 * Created by 11070564 on 2017/9/25.
 */

public class LoginActivity extends BascActivity {
    Button button ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        button=(Button)findViewById(R.id.broadcast_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                People people = new People();
                people.setAge(110);people.setName("Tony");

                Intent intent = new Intent(LoginActivity.this,MainBroadcastTest.class);
                intent.putExtra("people_data",people);
                startActivity(intent);
            }
        });
    }
}
