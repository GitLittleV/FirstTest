package com.example.a11070564.apidemotraining;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by 11070564 on 2017/9/19.
 */

public class DatabaseProvider extends ContentProvider {

    public static final  int BOOK_DIR=0;
    public static final  int BOOK_ITEM=1;
    public static final  int CATERGORY_DIR=2;
    public static final  int CATERGORY_ITEM=3;
    public static final  String AUTHORITY="com.example.a11070564.apidemotraining.provider";
    public static UriMatcher uriMatcher;
    //private MyDataBase dbHelper;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"contactsTable",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"contactsTable/#",BOOK_ITEM);
    }

    @Override
    public boolean onCreate() {
        //dbHelper = new MyDataBase(getContext());
        return  true;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteOpenHelper db =null ;//(SQLiteOpenHelper)dbHelper.getReadableDatabase();
        Cursor cursor=null;

        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
              //  cursor = db.query;
                break;
            case BOOK_ITEM:
              //  cursor = db.query;
                break;
            default: break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case  BOOK_DIR:
                return "vnd.android,cursor.dir/vnd.com.example.a11070564.apidemotraining.provider.book";
            case  BOOK_ITEM:
                return "vnd.android,cursor.item/vnd.com.example.a11070564.apidemotraining.provider.book";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
