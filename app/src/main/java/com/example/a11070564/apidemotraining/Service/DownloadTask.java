package com.example.a11070564.apidemotraining.Service;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by 11070564 on 2017/9/20.
 */

public class DownloadTask extends AsyncTask<Void ,Integer , Boolean> {
    private ProgressDialog progressDialog;
    @Override
    protected void onPreExecute() {
        progressDialog.show();

    }

    @Override
    protected Boolean doInBackground(Void... params) {

        try {
            while (true){
                //int downloadPercent = doDownload():
                //publishProgress(downloadPercent);
                //if (downloadPercent >= 100)
                    break;
            }

        } catch (Exception e) {
            return false;
        }
        return  true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setMessage("Download"+values[0]+"%");
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
       /* progressDialog.dismiss();
        if (result){
            Toast.makeText(context,"Download succeeded",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context,"Download failed",Toast.LENGTH_SHORT).show();
        }*/
        super.onPostExecute(aBoolean);
    }

    public static void main(String[] args) {
        new DownloadTask().execute();
    }
}
