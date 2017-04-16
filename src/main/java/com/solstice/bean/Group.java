package com.solstice.bean;

import com.alibaba.fastjson.JSONObject;

public class Group {
	//主键
	private int no;
	//当前登入的用户id，在移动端使用手机号
	private String id;
	//好友的id，与id生成联合主键
	private String fid;
	//好友分组名称
	private String groupName;
	
	public Group(){
		
	}
	
	public Group(String id, String fid){
		this.id = id;
		this.fid = fid;
	}
	
	
	public Group(String id, String fid, String groupName){
		this.id = id;
		this.fid = fid;
		this.groupName = groupName;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
