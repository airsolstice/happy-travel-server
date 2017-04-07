package com.solstice.service;

import java.util.List;

import com.solstice.bean.Goods;
import com.solstice.exception.UserException;

public interface GoodsService {
	//获取所有商品的数据
	public List<Goods> getGoodsList();
	//获取每类所有商品的数据
	public List<Goods> getGoodsListByCatalog(int catalog) throws UserException;
	//获取每类商品最新10条数据
	public List<Goods> getCatalogGoodsTop10(int catalog) throws UserException;
	//获取从第m条起的后面n条数据
	public List<Goods> getCatalogGoodsPage(int start,int length,int catalog) throws UserException;
	//获取一类型商品的总数
	public int getCatalogGoodsSum(int catalog) throws UserException;
	//通过商品名称关键字查找商品
	public List<Goods> findGoodsByKey(String key) throws UserException;
	//添加一个商品
	public void addGoods(Goods goods) throws UserException;
	//下架一个商品
	public void deleteGoods(int id);
	//修改商品名称
	public void updateName(Goods goods) throws UserException;
	//修改商品描述
	public void updateDetail(Goods goods) throws UserException;
	//修改商品价格
	public void updatePrice(Goods goods) throws UserException;
	//修改商品种类
	public void updateCatalog(Goods goods) throws UserException;
	//修改商品图片链接
	public void updateUrl(Goods goods) throws UserException;
}
