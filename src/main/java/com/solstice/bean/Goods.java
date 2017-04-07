package com.solstice.bean;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class Goods {
	//商品id，自增
	private int id = -1;
	//商品名称
	private String name;
	//商品描述
	private String detail;
	//商品价格
	private double price;
	//商品种类，1-5各映射相应的类型
	private int catalog;
	//商品图片链接，每种商品有多张图片链接，使用 ‘|’分隔
	private String url;
	//商品添加的时间，以此为基准判断是否为新品（可以取前十条，也可以根据时间戳取若干条数据）
	private Date addTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCatalog() {
		return catalog;
	}
	public void setCatalog(int catalog) {
		this.catalog = catalog;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
