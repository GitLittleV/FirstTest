package com.example.a11070564.apidemotraining;

import android.app.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

/**
 * Created by 11070564 on 2017/9/27.
 */

public class TakeMovie extends Activity{
    private Button play;
    private Button pause;
    private Button replay;

    private VideoView videoView ;
    public static final String TAG="myTakeMovie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        play=(Button)findViewById(R.id.play);
        pause=(Button)findViewById(R.id.pause);
        replay=(Button)findViewById(R.id.stop);
        videoView=(VideoView)findViewById(R.id.video_view);
        play.setOnClickListener(clickListener_play);
        pause.setOnClickListener(clickListener_pause);
        replay.setOnClickListener(clickListener_replay);

        initvideoPlayer();//编写初始化函数
    }

    private String path;
    private void initvideoPlayer(){

        try {
            path = Environment.getExternalStorageDirectory()+"/Movies/mymovie.mp4";
            //videoView.setVideoURI(Uri.parse("android.resource://"+R.raw.movie));
            videoView.setVideoPath(path);
            videoView.setMediaController(new MediaController(this));
            videoView.requestFocus();
        } catch (Exception e) {
            Log.d(TAG, "init"+e.getMessage());
        }
    }

    private View.OnClickListener clickListener_play = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d(TAG, "in 1");
            if (!videoView.isPlaying()){
                Log.d(TAG, "play : ");
                videoView.start();
                Toast.makeText(TakeMovie.this,"start",Toast.LENGTH_SHORT).show();

            }

        }
    };
    private View.OnClickListener clickListener_pause = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d(TAG, "in2 ");
            if (videoView.isPlaying()){
                Log.d(TAG, "pause : ");
                videoView.pause();
                Toast.makeText(TakeMovie.this,"pause",Toast.LENGTH_SHORT).show();


            }

        }
    };
    private View.OnClickListener clickListener_replay = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d(TAG, "in 3");
            if (videoView.isPlaying()) {
                Log.d(TAG, "replay : ");
                videoView.resume();//重置，音频会重头播放
                Toast.makeText(TakeMovie.this,"replay",Toast.LENGTH_SHORT).show();

            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView!=null){
            videoView.suspend();
            videoView = null;
        }
    }
}