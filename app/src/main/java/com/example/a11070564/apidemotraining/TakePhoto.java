package com.example.a11070564.apidemotraining;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a11070564.apidemotraining.R;

import java.io.File;
import java.io.FileNotFoundException;

import static android.provider.MediaStore.EXTRA_OUTPUT;

/**
 * Created by 11070564 on 2017/9/26.
 */

public class TakePhoto extends Activity {
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final String TAG="TakePhoto";
    private Button button;
    private ImageView image ;
    private Uri imageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        button=(Button)findViewById(R.id.button_photo);
        image=(ImageView)findViewById(R.id.image_picture);

        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //创建File对象，用于存储拍照照片。存在SD卡根目录Environment.getExternalStorageDirectory()
                File outputImage = new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
                try {
                    if (outputImage.exists()){
                        outputImage.delete();
                    }
                    boolean isSuc=outputImage.createNewFile();
                    
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                }

                //调用照相机程序
                //利用uri.fromFile将 File 转化成uri对象.其标识照片唯一地址
               imageUri= FileProvider.getUriForFile(TakePhoto.this,"com.example.a11070564.apidemotraining.fileprovider",outputImage);


                //构建Intent
                Intent intent =new Intent("android.media.action.IMAGE_CAPTURE");


                //指定图片输出地址，地址为刚刚uri后的地址
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                //使用隐式intent
                startActivityForResult(intent,TAKE_PHOTO);//启动相机程序
            }

        });
    }

    //拍完照返回到这里
    //剪裁完也回到这里
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "switch? "+requestCode);
        switch (requestCode){
            case  TAKE_PHOTO:
                Log.d(TAG, "resultCode? "+resultCode);
                if (resultCode == RESULT_OK){
                    Intent intent = new Intent("com.android.camera.action.CROP");

                    intent.setDataAndType(imageUri, "image/*");

                    intent.putExtra("crop", "true");
                    intent.putExtra("outputX", 80);
                    intent.putExtra("outputY", 80);
                    intent.putExtra("return-data", false);
                   // intent.putExtra("scale",true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    //再次启动跳转页面
                    startActivityForResult(intent,CROP_PHOTO);//启动裁剪程序
                }
                break;
            case CROP_PHOTO:
                if (requestCode == RESULT_OK){
                    try {
                        //建立Bitmap 用BitmapFactory解析刚才的照片为bmp
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        //显示出来
                        image.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        Log.d(TAG, ""+e.getMessage());
                    }
                }
                break;
            default:
                break;
        }


    }
}
