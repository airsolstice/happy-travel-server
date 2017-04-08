
package com.solstice.bean;

import com.alibaba.fastjson.JSONObject;


public class User {
	//用户id，在移动端使用手机号，在web端使用邮箱
	private String id;
	//用户名
	private String name;
	//密码
	private String pwd;
	//邮箱
	private String email;
	//0-保密，1-男，2-女
	private int sex = 0;
	//手机号码
	private String phone;
	//激活状态 1-已激活 ，0-未激活
	private int status = 0;
	//激活码
	private String activeCode;
	
	public User(){}
	
	//用户个人信息获取
	public User(String id, String name, String email, int sex, String phone){
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
