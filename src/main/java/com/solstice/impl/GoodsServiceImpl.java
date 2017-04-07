package com.solstice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.solstice.bean.Goods;
import com.solstice.exception.UserException;
import com.solstice.mapper.GoodsMapper;
import com.solstice.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsMapper goodMapper;
	
	@Override
	public List<Goods> getGoodsList() {
		List<Goods> result = goodMapper.getGoodsList();
		return result;
	}

	@Override
	public List<Goods> getGoodsListByCatalog(int catalog) throws UserException{
		
		List<Goods> result = null;
		
		if (catalog > 5 || catalog < 1){
			throw new UserException(
					String.format("参数[%s]不合法，必须在1-5之间", catalog));
		}
		
		result = goodMapper.getGoodsListByCatalog(catalog);
		
		return result;
	}

	@Override
	public List<Goods> getCatalogGoodsTop10(int catalog) throws UserException {
		
		List<Goods> result = null;
		
		if (catalog > 5 || catalog < 1){
			throw new UserException(
					String.format("参数[%s]不合法，必须在1-5之间", catalog));
		}
		
		result = goodMapper.getCatalogGoodsTop10(catalog);
		
		return result;
	}

	@Override
	public int getCatalogGoodsSum(int catalog) throws UserException{
		
		if (catalog > 5 || catalog < 1){
			throw new UserException(
					String.format("参数[%s]不合法，必须在1-5之间", catalog));
		}
		
		return goodMapper.getCatalogGoodsSum(catalog);
	}
	
	@Override
	public List<Goods> getCatalogGoodsPage(int start, int length, int catalog) 
			throws UserException{
		List<Goods> result = null;
		if (catalog > 5 || catalog < 1){
			throw new UserException(
					String.format("参数[%s]不合法，必须在1-5之间", catalog));
		}
		
		if (start < 0){
			throw new UserException(
					String.format("参数[%s]不合法，必须在1-5之间", catalog));
		}
		
		result = goodMapper.getCatalogGoodsPage(start, length, catalog);
		return result;
	}

	@Override
	public List<Goods> findGoodsByKey(String key) throws UserException{
		List<Goods> result = null;
		
		if (StringUtils.isEmpty(key)){
			throw new UserException("关键字为空");
		}
		
		StringBuffer keyword = new StringBuffer();
		keyword.append(key).append("%");
		result = goodMapper.findGoodsByKey(keyword.toString());
		
		return result;
	}

	@Override
	public void addGoods(Goods goods) throws UserException{
		
		if(goods == null){
			throw new UserException("添加的对象为空");
		}
		
		int catalog = goods.getCatalog();
		if(catalog >5 || catalog < 1){
			throw new UserException(String.format("catalog[%s]超过范围1-5", catalog));
		}
		
		if (goods.getAddTime() == null){
			throw new UserException("商品的添加时间为空");
		}
		
		goodMapper.addGoods(goods);
	}

	@Override
	public void deleteGoods(int id){
		goodMapper.deleteGoods(id);
	}

	@Override
	public void updateName(Goods goods) throws UserException{
		if (goods.getId() == -1){
			throw new UserException("id未设置，请设置id");
		}
		
		if(goods.getName() == null){
			throw new UserException("字段为空，请重新输入");
		}
		
		goodMapper.updateName(goods);
	}

	@Override
	public void updateDetail(Goods goods) throws UserException{
		if (goods.getId() == -1){
			throw new UserException("id未设置，请设置id");
		}
		
		if(goods.getDetail() == null){
			throw new UserException("字段为空，请重新输入");
		}
		
		goodMapper.updateDetail(goods);
		
	}

	@Override
	public void updatePrice(Goods goods) throws UserException{
		if (goods.getId() == -1){
			throw new UserException("id未设置，请设置id");
		}
		
		goodMapper.updatePrice(goods);
	}

	@Override
	public void updateCatalog(Goods goods) throws UserException{
		if (goods.getId() == -1){
			throw new UserException("id未设置，请设置id");
		}
		
		if(goods.getCatalog() > 5 || goods.getCatalog() < 1){
			throw new UserException(String.format("catalog[%s]超出范围1-5", 
					goods.getCatalog()));
		}
		
		goodMapper.updateCatalog(goods);
	}

	@Override
	public void updateUrl(Goods goods) throws UserException{
		if (goods.getId() == -1){
			throw new UserException("id未设置，请设置id");
		}
		
		if(goods.getUrl() == null){
			throw new UserException("字段为空，请重新输入");
		}
		
		goodMapper.updateUrl(goods);
	}

}
