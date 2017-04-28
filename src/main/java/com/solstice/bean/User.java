
package com.solstice.bean;

import com.alibaba.fastjson.JSONObject;


public class User {
	//用户id，在移动端使用手机号
	private String id;
	//用户名
	private String name = "new_user";
	//密码
	private String pwd;
	//邮箱
	private String email;
	//0-保密，1-男，2-女
	private int sex = 0;
	//手机号码
	private String phone;
	//状态 1-在线 ，0-离线
	private int status = 0;
	//头像链接
	private String url;
	//经度
	private double lat;
	//纬度
	private double lng;
	//说明
	private String note;
	
	private int chatId;
	
	public User(){}

	
	public User(String id, int status){
		this.id = id;
		this.status = status;
	}
	
	//用户登入
	public User(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public int getChatId() {
		return chatId;
	}


	public void setChatId(int chatId) {
		this.chatId = chatId;
	}


	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
