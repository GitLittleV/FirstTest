package com.example.a11070564.apidemotraining;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11070564 on 2017/9/19.
 */

public class ContentsTest extends Activity{
    ListView listView;
    ArrayAdapter <String>adapter ;
    List<String> contentList = new ArrayList<>();
    public static final String TAG = "ContentsTest";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentslist);

        listView = (ListView)findViewById(R.id.contentview);
        //readContacts();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contentList);
        listView.setAdapter(adapter);
        readContacts();
    }

    private void readContacts(){
        Cursor cursor= null;

        try {
            //Access Contract's Uri
            cursor= getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while (cursor.moveToNext()){
                //ContractName
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                //CpntractNumber
                String displayNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                //问题 cursor遇到空提前结束 产生Exception
               // String displayType = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE));

              //  Log.d(TAG,displayName+"\n"+displayNumber+"##Type"+displayType);
                contentList.add(displayName+"\n"+displayNumber);

            }

        } catch (Exception e) {
            Log.d(TAG,e.getMessage());
        }
        finally {
            if (cursor!=null)
                cursor.close();
        }

    }
}
