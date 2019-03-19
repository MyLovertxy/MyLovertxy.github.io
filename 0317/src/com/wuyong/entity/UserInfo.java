package com.wuyong.entity;

public class UserInfo {

	private int userid;
	private String username;
	private String upassword;
	private String sex;
	private int age;
	public UserInfo() {
		
	}
	public UserInfo(String username, String upassword, String sex, int age
						) {
		super();
		this.username = username;
		this.upassword = upassword;
		this.sex = sex;
		this.age = age;
	}




	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	




	public UserInfo(int userid, String username, String upassword, String sex,
			int age) {
		super();
		this.userid = userid;
		this.username = username;
		this.upassword = upassword;
		this.sex = sex;
		this.age = age;
	}

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	
}
