package com.example.a11070564.apidemotraining;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by 11070564 on 2017/9/26.
 */

public class TakeMusic extends Activity implements View.OnClickListener{
    private Button play;
    private Button pause;
    private Button stop;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    public static final String TAG="TakeMusic";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        play=(Button)findViewById(R.id.play);
        pause=(Button)findViewById(R.id.pause);
        stop=(Button)findViewById(R.id.stop);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        initMediaPlayer();//编写初始化函数
    }
    private void initMediaPlayer(){

        File file = new File(Environment.getExternalStorageDirectory(),"music.mp3");
        Log.d(TAG, "File paths : "+file.getAbsolutePath());
        Log.d(TAG, "File exist? : "+file.exists());
        try {
            Log.d(TAG, "File getpath : "+file.getPath());
            mediaPlayer.setDataSource(file.getPath());//指定音频文件路径

            mediaPlayer.prepare();//让mediaplay进入准备

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.play:
                Log.d(TAG, "in 1");
                if (!mediaPlayer.isPlaying()){
                    Log.d(TAG, "play : ");
                    mediaPlayer.start();
                    Toast.makeText(this,"start",Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.pause:
                Log.d(TAG, "in2 ");
                if (mediaPlayer.isPlaying()){
                    Log.d(TAG, "pause : ");
                    mediaPlayer.pause();
                    Toast.makeText(this,"pause",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.stop:
                Log.d(TAG, "in 3");
                if (mediaPlayer.isPlaying()) {
                    Log.d(TAG, "stop : ");
                    mediaPlayer.reset();//重置，音频会重头播放
                    initMediaPlayer();
                    Toast.makeText(this,"stop",Toast.LENGTH_SHORT).show();
                }
                break;

            default:break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
