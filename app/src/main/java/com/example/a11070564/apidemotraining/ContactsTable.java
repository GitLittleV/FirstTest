package com.example.a11070564.apidemotraining;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.Vector;

/**
 * Table创建类
 * @author HPZ
 *
 */
public class ContactsTable {
	private final static String TABLENAME ="contactsTable";//表名
	private MyDataBase db;//数据库管理对象

	public ContactsTable(Context context){
		//打开数据库写指针
		db=new MyDataBase(context);

		if(!db.isTableExist(TABLENAME)){
		/*String  createTableSql=
		" CREATE　TABLE  IF NOT EXISTS "+TABLENAME+
		" ( id_DB  integer  primary  key  autoincrement  not null,"+
		User.NAME+"  VARCHAR,"+
		User.MOBLIE+"  VARCHAR,"+
		User.QQ+"  VARCHAR,"+
		User.COMPANY+"  VARCHAR,"+
		User.ADDRESS+"  VARCHAR )";*/

			String createTableSql="create table contactsTable " +
					"(  id_DB   integer PRIMARY KEY AUTOINCREMENT NOT NULL," +
					"name varchar,moblie varchar,company varchar," +
					"qq varchar, address  varchar)";
			//生成表
			db.creatTable(createTableSql);

		}

	}
	//获取所有联系人
	public User[] getAllUsers() {
		Vector<User> vector =new Vector<User>();
		Cursor cursor = null;
		try {
			cursor=db.findSelect("select *  from "+TABLENAME, null);

			//查找全表，将匹配信息存入Vector
			while (cursor.moveToNext()) {
				User user = new User();
				user.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));//name的名称获得它的列索引，
				user.setName(cursor.getString(cursor.getColumnIndex("name")));
				user.setMoblie(cursor.getString(cursor.getColumnIndex("moblie")));
				user.setQq(cursor.getString(cursor.getColumnIndex("qq")));
				user.setAddress(cursor.getString(cursor.getColumnIndex("address")));
				user.setCompany(cursor.getString(cursor.getColumnIndex("company")));

				vector.add(user);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(cursor!=null){
				cursor.close();
			}
			db.closeConnection();
		}

		if(vector.size()>0){
			//强Vector数据强转User[]并返回
			return vector.toArray(new User[]{});
		}else {
			User [] users=new User[1];
			User user=new User();
			user.setName("暂无用户");
			users[0]=user;
			return users;
		}
	}

	//新增数据
	public boolean addData(User user) {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		values.put(User.NAME, user.getName());
		values.put(User.MOBLIE, user.getMoblie());
		values.put(User.COMPANY, user.getCompany());
		values.put(User.QQ, user.getQq());
		values.put(User.ADDRESS, user.getAddress());

		return db.insertTable(TABLENAME, values);
	}

	//根据主键ID查找
	public User getUserByID(int id){
		Cursor cursor = null;
		try {
			cursor= db.findSelect("select *  from  "+TABLENAME+"  where "+"id_DB=?", new String[]{id+""});
			User user=new User();
			//游标指向-1位置，moveToNext
			cursor.moveToNext();
			user.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));//name的名称获得它的列索引，
			user.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
			user.setMoblie(cursor.getString(cursor.getColumnIndex(User.MOBLIE)));
			user.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
			user.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
			user.setCompany(cursor.getString(cursor.getColumnIndex(User.COMPANY)));
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(cursor!=null){
				cursor.close();
			}
			db.closeConnection();
		}
		return null;
	}
	//修改用户数据
	public boolean updataUser(User user) {
		ContentValues values=new ContentValues();
		values.put(User.NAME, user.getName());
		values.put(User.MOBLIE, user.getMoblie());
		values.put(User.COMPANY, user.getCompany());
		values.put(User.QQ, user.getQq());
		values.put(User.ADDRESS, user.getAddress());

		return db.updataTable(TABLENAME, values, " id_DB=? ", new String[]{user.getId_DB()+""});


	}
	//删除联系人
	public boolean deleteByUser(User user) {
		return db.deleteTable(TABLENAME, "  id_DB=?", new String[] {user.getId_DB()+""});

	}
	//模糊匹配联系人
	public User[] findUserByKey(String key) {
		Vector<User> vector =new Vector<User>();
		Cursor cursor=null;

		try {
			cursor=db.findSelect("select *  from "+TABLENAME+"  where  "+
					User.NAME+"  like '%"+key+"%' "+
					" or "+User.MOBLIE+"  like '%"+key+"%' "+
					" or "+User.QQ+"  like '%"+key+"%' ", null);
			//查找全表，将匹配信息存入Vector
			while (cursor.moveToNext()) {
				User user = new User();
				user.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));//name的名称获得它的列索引，
				user.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
				user.setMoblie(cursor.getString(cursor.getColumnIndex(User.MOBLIE)));
				user.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
				user.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
				user.setCompany(cursor.getString(cursor.getColumnIndex(User.COMPANY)));

				vector.add(user);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(cursor!=null){
				cursor.close();
			}
			db.closeConnection();
		}

		if(vector.size()>0){
			//强Vector数据强转User[]并返回
			return vector.toArray(new User[]{});
		}else {
			User [] users=new User[1];
			User user=new User();
			user.setName("匹配失败");
			users[0]=user;
			return users;
		}
	}



}
