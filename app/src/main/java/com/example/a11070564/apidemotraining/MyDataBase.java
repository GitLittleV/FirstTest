package com.example.a11070564.apidemotraining;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库操作类
 * @author HPZ
 *
 */
public class MyDataBase extends SQLiteOpenHelper {

	private static String DB_NAME ="MY_DB.db";

	private static int DB_VERSION=2;

	private SQLiteDatabase db;

	public MyDataBase(Context context){
		super(context, DB_NAME, null, DB_VERSION);
		db=getWritableDatabase();
	}
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	//打开SQL数据库连接
	public SQLiteDatabase openConnection() {
		// TODO Auto-generated method stub

		if(!db.isOpen()){
		db=this.getWritableDatabase();

		}
		return db;

	}

//关闭数据库
	public void closeConnection() {
		// TODO Auto-generated method stub
		try {
			if(db!=null&&db.isOpen())
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	//创建表
	public boolean creatTable(String createTableSql) {
		// TODO Auto-generated method stub
		try {
			openConnection();
			db.execSQL(createTableSql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally{
			closeConnection();
		}
		return true;

	}

	//添加数据
	public boolean insertTable(String tableName, ContentValues values) {
		// TODO Auto-generated method stub
		try {
			openConnection();
			long i=db.insert(tableName, null, values);
			if(i==-1){
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally{
			closeConnection();
		}
		return true;

	}
	///修改数据
	public boolean updataTable(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
		// TODO Auto-generated method stub
		try {
			openConnection();
			db.update(tableName, values, whereClause, whereArgs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally{
			closeConnection();
		}
		return true;

	}

	//删除数据
	public boolean deleteTable(String tableName, String deleteSql, String obj[]) {
		// TODO Auto-generated method stub
		try {
			openConnection();
			db.delete(tableName, deleteSql, obj);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally{
			closeConnection();
		}
		return true;

	}
	//查询
	public Cursor findSelect(String sql, String obj[]) {
		// TODO Auto-generated method stub
		try {
			openConnection();
			Cursor cursor=null;
			cursor=db.rawQuery(sql, obj);

			return cursor;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
	//查找表是否存在
	public boolean isTableExist(String tableName) {
		   boolean result = false;
           if(tableName == null){
                   return false;
           }

		Cursor cursor=null;
		try {
			openConnection();
			//String str="select count(*) from sqlite_master where type='table' and name='"+tableName+"'";
			String sql = "SELECT count(*) FROM   sqlite_master   where type ='table' and name ='"+tableName.trim()+"' ";
			cursor = db.rawQuery(sql, null);
			  if(cursor.moveToNext()){
                  int count = cursor.getInt(0);
                  if(count>0){
                          result = true;

                  }

          }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			return result;
		}finally{
			if(cursor!=null){
				cursor.close();
			}
			closeConnection();
		}
		return result;


	}



}
