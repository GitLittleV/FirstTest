package com.example.a11070564.apidemotraining;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a11070564.apidemotraining.R;

/**
 * Created by 11070564 on 2017/9/28.
 */

public class TakePhotoFromAlbum extends Activity {

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int TAKE_THU_PHOTO = 3;
    public static final int CHOOSE_PHOTO = 4;

    public static final String TAG="myTakePhotoFromAlbum";
    private Button choose;
    private ImageView photo ;
    private Uri imageUri;
    private String tempPhotoName;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        choose=(Button)findViewById(R.id.button_photo);
        photo=(ImageView)findViewById(R.id.image_picture);

        //photo.setImageBitmap(BitmapFactory.decodeFile(getResources().getResourceName(R.raw.photo)));
        //photo.setImageResource(R.drawable.photo);


        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开相册，完成Intent
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent,CHOOSE_PHOTO);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "switch? " + requestCode);
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO:
                Log.d(TAG, "resultCode " + resultCode +" ?= RESULT_OK"+RESULT_OK);
                if (resultCode==RESULT_OK){

                    Log.d(TAG,"SDK "+Build.VERSION.SDK_INT );
                    if (Build.VERSION.SDK_INT >= 19){
                        //新版本涉及Uri封装解析过程
                        handleImageOnKitKat(data);
                    }else {
                        //旧版不需要解析
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:break;
        }
    }


    private void handleImageOnKitKat(Intent data) {
        Log.d(TAG, "handleImageOnKitKat");
        String imagePath = null;
        Uri uri = data.getData();
        //document类型
        if (DocumentsContract.isDocumentUri(this,uri)){
            Log.d(TAG, "handleImageOnKitKat: isDocumentUri");
            //如果是document类型Uri，则通过document id 处理
            String docId= DocumentsContract.getDocumentId(uri);
                //如果getAuthority是media格式则再次解析
                if ("com.android.providers.media.documents".equals(uri.getAuthority())){

                    String id = docId.split(":")[1];
                    String selection = MediaStore.Images.Media._ID+"="+id;
                    Log.d(TAG, "handleImageOnKitKat: media id "+ selection);
                    imagePath = getImagePath(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection
                    );

                }else
                    //getAuthority正常
                    if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                        Log.d(TAG, "handleImageOnKitKat: downloads");
                    Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId)
                    );
                    imagePath = getImagePath(contentUri,null);
                }

        }else
            //如果不是document类型Uri，普通方式
            if ("content".equalsIgnoreCase(uri.getScheme())){
            //不是document类型的id
            imagePath = getImagePath(uri,null);
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Log.d(TAG, "handleImageBeforeKitKat");
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);

        if (cursor!=null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                Log.d(TAG, "getImagePath: path "+path);
            }
            cursor.close();
        }

                return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath !=null){
            Bitmap bit
                    = BitmapFactory.decodeFile(imagePath);
            Log.d(TAG, "displayImage: "+bit.getByteCount() / 1024
                    + "KB");

            //图片压缩
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = 4;
            options2.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bm = BitmapFactory.decodeFile(imagePath, options2);
            Log.d(TAG, "压缩后图片的大小" + (bm.getByteCount() / 1024)
                    + "KB 宽度为" + bm.getWidth() + "高度为" + bm.getHeight());

//            Bitmap bm = Bitmap.createScaledBitmap(bit, 150, 150, true);
//            Log.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024) + "KB宽度为"
//                    + bm.getWidth() + "高度为" + bm.getHeight());

            photo.setImageBitmap(bm);
        }else {
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }
}
