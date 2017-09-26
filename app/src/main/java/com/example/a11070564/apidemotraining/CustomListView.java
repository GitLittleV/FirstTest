package com.example.a11070564.apidemotraining;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by 11070564 on 2017/9/16.
 */

public class CustomListView extends Activity {

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.layout_custom_listview,PHOTOS_NAMES);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
