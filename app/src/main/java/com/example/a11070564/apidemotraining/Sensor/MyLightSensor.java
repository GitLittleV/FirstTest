package com.example.a11070564.apidemotraining.Sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.text.DecimalFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.a11070564.apidemotraining.R;

/**
 * Created by 11070564 on 2017/9/26.
 */

public class MyLightSensor extends Activity {
    private SensorManager sensorManager;
    private TextView lightLevel1;
    private TextView x;
    private TextView y;
    private TextView z;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);
        lightLevel1 = (TextView)findViewById(R.id.light_text1);
        x = (TextView)findViewById(R.id.accelerometer_x);
        y = (TextView)findViewById(R.id.accelerometer_y);
        z = (TextView)findViewById(R.id.accelerometer_z);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        Sensor sensor2 = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listener2,sensor2,SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager!=null)
            sensorManager.unregisterListener(listener);
        if (sensorManager!=null)
            sensorManager.unregisterListener(listener2);
    }

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //values数组第一个下表即时光强
            float value = event.values[0];
            lightLevel1.setText("Current light level is "+value+" lx");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    private SensorEventListener listener2 = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //values数组第一个下表即时光强

            float xValue = event.values[0];
            float yValue = event.values[1];
            float zValue = event.values[2];
            DecimalFormat df = new DecimalFormat("0.00");
            x.setText(""+df.format(xValue));
            y.setText(""+df.format(yValue));
            z.setText(""+df.format(zValue));

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
