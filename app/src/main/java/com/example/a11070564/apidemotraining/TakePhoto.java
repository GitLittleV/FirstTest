package com.example.a11070564.apidemotraining;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a11070564.apidemotraining.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.provider.MediaStore.EXTRA_OUTPUT;

/**
 * Created by 11070564 on 2017/9/26.
 */

public class TakePhoto extends Activity {
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int TAKE_THU_PHOTO = 3;
    public static final String TAG="myTakePhoto";
    private Button button;
    private ImageView image ;
    private Uri imageUri;
    private String tempPhotoName;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        button=(Button)findViewById(R.id.button_photo);
        image=(ImageView)findViewById(R.id.image_picture);
        image.setImageResource(R.drawable.photo2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }

    private File createImageFile() throws IOException{

        Log.d(TAG, "createImageFile:");
        String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_"+timeStamp+"_";

        //设置自定义路径目录在data文件下
        //Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);相机路径
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM+"/Camera");
        File image = File.createTempFile(imageFileName,".jpg",storageDir);
        //File image = new File(storageDir+"/"+imageFileName+".jpg");
        Log.d(TAG, "createImageFile: image = "+image.getAbsolutePath());
        return image;
    }

    private void takePicture(){
        //获得自定义路径 需要Uri Context：//
        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (photoIntent.resolveActivity(getPackageManager())!=null){
            File photoFile = null;
            try {
                photoFile =createImageFile();
            }catch (IOException ex) {
                Log.d(TAG, "dispatchTakePicture: " + ex.getMessage());
            }
            tempPhotoName=photoFile.getAbsolutePath();
            if (photoFile!=null){
                 imageUri =
                         FileProvider.getUriForFile
                                 (this, "com.example.a11070564.apidemotraining.fileprovider", photoFile);
                Log.d(TAG, "dispatchTakePicture: URI= "+imageUri.toString());
                List<ResolveInfo> resInfoList = getPackageManager() .queryIntentActivities(
                        photoIntent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList)
                {
                    String packageName = resolveInfo.activityInfo.packageName;
                    grantUriPermission(packageName, imageUri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                //向照片输出路径进行指定，在photoUri指定的地方
                photoIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(photoIntent,TAKE_PHOTO);
            }
        }


    }
    private void takeThumbnailIntent() {
        Log.d(TAG,"takeThumbnailIntent");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, TAKE_THU_PHOTO);
        }
    }
    private void takeDefaultPicture(){
//        //获得相机返回路径
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, TAKE_PHOTO);
        //获得图库路径
        Intent intent2 = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent2, TAKE_PHOTO);
    }
    //拍完照返回到这里
    //剪裁完也回到这里
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "switch? "+requestCode);
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case  TAKE_PHOTO:
                Log.d(TAG, "resultCode? "+resultCode);
                if (resultCode == RESULT_OK){

                    if (data!=null){
                        //发现问题  查看源码
                        //如果指定了照片存储路径则 data在大多数机型返回null 需要利用其他方式记录
                        Bundle extra = data.getExtras();
                        if (extra!=null){
                            Bitmap bitmap = (Bitmap)extra.get("data");
                           image.setImageBitmap(bitmap);
                        }else {
                            Log.d(TAG, "no Bitmap return  ");
                        }
                    }else {


//                        try {
//                            Intent intent = new Intent("com.android.camera.action.CROP");
//                            intent.setDataAndType(imageUri, "image/*");
//                            intent.putExtra("scale", "true");
//                          intent.putExtra("outputX", 80);
//                          intent.putExtra("outputY", 80);
//                            intent.putExtra("return-data", false);
//                            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
//                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                            //再次启动跳转页面
//                            startActivityForResult(intent,CROP_PHOTO);//启动裁剪程序
//                        } catch (Resources.NotFoundException e) {
//                            Log.d(TAG, "TAKE_PHOTO " +e.getMessage());
//                    }

                        BitmapFactory.Options options2 = new BitmapFactory.Options();
                        options2.inSampleSize = 4;
                        options2.inPreferredConfig = Bitmap.Config.RGB_565;
                        Bitmap bm = BitmapFactory.decodeFile(tempPhotoName, options2);
                        Log.d(TAG, "压缩后图片的大小" + (bm.getByteCount() / 1024)
                                + "KB 宽度为" + bm.getWidth() + "高度为" + bm.getHeight());
                        image.setImageBitmap(bm);
                    }
                }

                break;
            case CROP_PHOTO:
                if (requestCode == RESULT_OK){
                        if (imageUri!=null) {

//                            try {
//                            Log.d(TAG, "data is null ");
//
//                                Bitmap bitmap =BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
//                                Log.d(TAG, "File size :"+bitmap.getByteCount()/1021+"kb"+"File path :"+tempPhotoName);
//
//
//                                image.setImageBitmap(bitmap);
//                            } catch (FileNotFoundException e) {
//                                Log.d(TAG, "RESULT_OK " + e.getMessage());
//                            }
                            BitmapFactory.Options options2 = new BitmapFactory.Options();
                            options2.inSampleSize = 4;
                            options2.inPreferredConfig = Bitmap.Config.RGB_565;
                            Bitmap bm = BitmapFactory.decodeFile(tempPhotoName, options2);
                            Log.d(TAG, "压缩后图片的大小" + (bm.getByteCount() / 1024)
                                    + "KB 宽度为" + bm.getWidth() + "高度为" + bm.getHeight());
                            image.setImageBitmap(bm);
                        }
                }
                break;
            case TAKE_THU_PHOTO:
                if (resultCode==RESULT_OK){
                    if(data != null){
                        Bundle extras = data.getExtras();
                        if(extras != null){
                            Bitmap imageBitmap = (Bitmap) extras.get("data");
                            image.setImageBitmap(imageBitmap);
                        }else{
                            Log.d(TAG,"no Bitmap return");
                        }
                    }else{
                        Log.d(TAG,"data is null");
                    }
                }
                break;
            default:
                break;
        }


    }
}
