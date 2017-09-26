package com.example.a11070564.apidemotraining;

import android.content.pm.ApplicationInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class Transition3d extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
    private ListView mPhotosList;
    private ViewGroup mContainer;
    private ImageView mImageView;

    // Names of the photos we show in the list
    private static final String[] PHOTOS_NAMES = new String[] {
            "Lyon",
            "Livermore",
            "Tahoe Pier",
            "Lake Tahoe",
            "Grand Canyon",
            "Bodie"
    };

    // Resource identifiers for the photos we want to display
    private static final int[] PHOTOS_RESOURCES = new int[] {
            R.drawable.photo1,
            R.drawable.photo2,
            R.drawable.photo3,
            R.drawable.photo4,
            R.drawable.photo5,
            R.drawable.photo6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animations_main_screen);
        mPhotosList=(ListView)findViewById(android.R.id.list);
        mImageView=(ImageView) findViewById(R.id.picture);
        mContainer=(ViewGroup) findViewById(R.id.container);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,PHOTOS_NAMES);
        mPhotosList.setAdapter(adapter);
        mPhotosList.setOnItemClickListener(this);
        mImageView.setOnClickListener(this);
        mContainer.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);

    }

    //
    private void applyRotation (int postion ,float start ,float end){
        final float centerX = mContainer.getWidth()/2.0f;
        final float centerY = mContainer.getHeight()/2.0f;

        //1000.0F 控制Z轴旋转深度  数值越大进入深度越大
        final Rotate3dAnimation rotation = new Rotate3dAnimation(start,end,centerX,centerY,1000.0f,true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator( new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView(postion));
        mContainer.startAnimation(rotation);
    }
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // Pre-load the image then start the animation
        mImageView.setImageResource(PHOTOS_RESOURCES[position]);
        //运行动画效果 位置，起始0度，终点90度
        applyRotation(position, 0, 90);
    }

    //运行动画效果 复位，起始180度，终点90度
    public void onClick(View v) {
        applyRotation(-1, 360, 270);
    }
    /**
     * This class listens for the end of the first half of the animation.
     * 这个类监听第一次动画完成一半的事件
     * It then posts a new action that effectively swaps the views when the container
     * 当容器旋转了90度时，然后发送一个新的有效动画交换两个视图
     * is rotated 90 degrees and thus invisible.
     *
     */
    private final class DisplayNextView implements Animation.AnimationListener {
        private final int mPosition;

        private DisplayNextView(int position) {
            mPosition = position;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            mContainer.post(new SwapViews(mPosition));
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }


    /**
     * This class is responsible for swapping the views and start the second
     * 这个类来响应交换的视图，并且开始下一半动画效果
     * half of the animation.
     */
    private final class SwapViews implements Runnable {
        private final int mPosition;

        public SwapViews(int position) {
            mPosition = position;
        }

        public void run() {
            final float centerX = mContainer.getWidth() / 2.0f;
            final float centerY = mContainer.getHeight() / 2.0f;
            Rotate3dAnimation rotation;

            if (mPosition > -1) {
                //设置list不可见，image可见
                mPhotosList.setVisibility(View.GONE);
                mImageView.setVisibility(View.VISIBLE);
                mImageView.requestFocus();

                //这一段动画图片旋转完成另一半效果  0-90list消失   90-180image出现
                rotation = new Rotate3dAnimation(270, 360, centerX, centerY, 1000, false);
            }
            else {
                mImageView.setVisibility(View.GONE);
                mPhotosList.setVisibility(View.VISIBLE);
                mPhotosList.requestFocus();
                //这一段动画图片旋转完成另一半效果  180-90image消失   90-0list出现
                rotation = new Rotate3dAnimation(90, 0, centerX, centerY,1000, false);
            }

            //旋转时间500毫秒
            rotation.setDuration(500);
            //set true 动画保持在结束的位置
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());

            mContainer.startAnimation(rotation);
        }
    }
}
