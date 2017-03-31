package com.solstice.bean;

import com.alibaba.fastjson.JSONObject;

public class MapModel {
	private int id;
	//好友id
	private int ugId;
	//好友所在分组名称
	private String groupName;
	//好友名称
	private String userName;
	//好友头像URL
	private String headUrl;
	//好友定位的经度
	private double lat;
	//好友定位的纬度
	private double lng;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUgId() {
		return ugId;
	}
	public void setUgId(int ugId) {
		this.ugId = ugId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
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
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
