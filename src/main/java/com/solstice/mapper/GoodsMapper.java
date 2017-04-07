package com.solstice.mapper;

import java.util.List;

import com.solstice.bean.Goods;

public interface GoodsMapper {
	//获取所有商品的数据
	public List<Goods> getGoodsList();
	//获取每类所有商品的数据
	public List<Goods> getGoodsListByCatalog(int catalog);
	//获取每类商品最新10条数据
	public List<Goods> getCatalogGoodsTop10(int catalog);
	//获取从第m条起的后面n条数据
	public List<Goods> getCatalogGoodsPage(int start,int length,int catalog);
	//获取一类型商品的总数
	public int getCatalogGoodsSum(int catalog);
	//通过商品名称关键字查找商品
	public List<Goods> findGoodsByKey(String key);
	//添加一个商品
	public void addGoods(Goods goods);
	//下架一个商品
	public void deleteGoods(int id);
	//修改商品名称
	public void updateName(Goods goods);
	//修改商品描述
	public void updateDetail(Goods goods);
	//修改商品价格
	public void updatePrice(Goods goods);
	//修改商品种类
	public void updateCatalog(Goods goods);
	//修改商品图片链接
	public void updateUrl(Goods goods);
}
