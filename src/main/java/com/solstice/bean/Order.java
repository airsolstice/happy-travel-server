package com.solstice.bean;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;

public class Order {
	
	/**
	 * 订单头信息
	 */
	//订单流水号，形如：20170406111803
	private String no;
	//买家姓名
	private String buyer;
	//收货地址（不做格式控制，由前端完成）
	private String addr;
	//联系方式 手机，固话
	private String tel;
	//订单进行状态  0-未完成，1-进行中，2-已完成
	private int state;
	
	/**
	 * 订单内容
	 */
	private ArrayList<OrderInfo> info;
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public ArrayList<OrderInfo> getInfo() {
		return info;
	}

	public void setInfo(ArrayList<OrderInfo> info) {
		this.info = info;
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}

class OrderInfo {
	//商品id
	private int gid;
	//购买的数量
	private int sum;
	//单价
	private double price;
	//一张商品的展示图片
	private String url;

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	} 
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}

