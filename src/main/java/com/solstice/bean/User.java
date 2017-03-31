
package com.solstice.bean;


public class User {
	private int id;
	//用户名
	private String name;
	//密码
	private String pwd;
	//邮箱
	private String email;
	//性别 1男2女
	private int sex;
	//手机号码
	private String phone;
	//激活状态 1:已激活 0:未激活
	private int status;
	//激活码
	private String activeCode;
	
	public User(){}
	
	//用户个人信息获取
	public User(int id, String name, String email, int sex, String phone){
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
