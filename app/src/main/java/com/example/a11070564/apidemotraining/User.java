package com.example.a11070564.apidemotraining;
public class User {
	
	public final static String NAME="name";
	public final static String MOBLIE="moblie";
	public final static String COMPANY="company";
	public final static String QQ="qq";
	public final static String ADDRESS="address";
	
	

	private String name ;
	private String moblie ;
	private String company ;
	private String qq ;
	private String address ;
	private int   id_DB=-1;
	
	
	public int getId_DB() {
		return id_DB;
	}
	public void setId_DB(int id_DB) {
		this.id_DB = id_DB;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoblie() {
		return moblie;
	}
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
