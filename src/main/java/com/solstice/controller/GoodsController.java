package com.solstice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solstice.bean.Goods;
import com.solstice.bean.GoodsCatalog;
import com.solstice.bean.Result;
import com.solstice.bean.ResultCode;
import com.solstice.exception.UserException;
import com.solstice.service.GoodsService;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);
	
	@Autowired
	private GoodsService goodService;
	
	private  static final int PAGAE_LEN = 20;
	
	/**
	 * 获取分页页数
	 * @param request
	 * @param catalog
	 * @return
	 */
	@RequestMapping(value = "/catalog/size")
	public Result getCatalogPageSize(HttpServletRequest request, int catalog) {
		Result result = null;
		try {
			int sum = goodService.getCatalogGoodsSum(catalog);
			
			if(sum <= 0){
				result = new Result(ResultCode.FAIL, "暂无"+
						GoodsCatalog.valueOf(catalog)+"类商品", 0);
			}
			else {
				result = new Result(ResultCode.SUCESS, "获取商品分页数",sum % PAGAE_LEN + 1);
			}
		}
		catch (UserException e) {
			result = new Result(ResultCode.FAIL, "出现异常", null);
			LOGGER.info(e.getMessage());
		}
		catch (Exception e) {
			result = new Result(ResultCode.FAIL, "出现异常", null);
			e.printStackTrace();
		} 
		return result;
	}
	
	
	
	/**
	 * 获取单个类别的商品(分页)
	 *
	 * @param index
	 * @param request
	 * @param catalog
	 * @return
	 */
	@RequestMapping(value = "/catalog/page")
	public Result getCatalogGoodsPage(HttpServletRequest request, int index, int catalog) {
		Result result = null;
		try {
			//前端需要输入的index必须大于0
			int start = (index -1) * PAGAE_LEN;
			int length = PAGAE_LEN;
			List<Goods> list = goodService.getCatalogGoodsPage(start, length, catalog); 
			
			if(list == null){
				result = new Result(ResultCode.FAIL, "获取对象为空", null);
			}
			else if (list.size() == 0) {
				result = new Result(ResultCode.SUCESS, "暂无"+
						GoodsCatalog.valueOf(catalog)+"类商品",list);
			}
			else {
				result = new Result(ResultCode.SUCESS, String.format("获取"+
						GoodsCatalog.valueOf(catalog) +"类第%s页商品", index),list);
			}
		}
		catch (UserException e) {
			result = new Result(ResultCode.FAIL, "出现异常", null);
			LOGGER.info(e.getMessage());
		}
		catch (Exception e) {
			result = new Result(ResultCode.FAIL, "出现异常", null);
			e.printStackTrace();
		} 
		
		return result;
	}
	
	/**
	 * 获取指定类型商品的前十条数据
	 * 
	 * @param request
	 * @param catalog
	 * @return
	 */
	@RequestMapping(value = "/catalog/top10")
	public Result getCatalogGoodsTop10(HttpServletRequest request, int catalog) {
		Result result = null;
		try {
			List<Goods> list = goodService.getCatalogGoodsTop10(catalog); 
			
			if(list == null){
				result = new Result(ResultCode.FAIL, "获取对象为空", null);
			}
			else if (list.size() == 0) {
				result = new Result(ResultCode.SUCESS, "暂无"+
						GoodsCatalog.valueOf(catalog)+"类商品",list);
			}
			else {
				result = new Result(ResultCode.SUCESS, "获取"+
						GoodsCatalog.valueOf(catalog) +"类商品TOP 10",list);
			}
		}
		catch (UserException e) {
			result = new Result(ResultCode.FAIL, "出现异常", null);
			LOGGER.info(e.getMessage());
		}
		catch (Exception e) {
			result = new Result(ResultCode.FAIL, "出现异常", null);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 商品搜索
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/search")
	public Result findGoodsByKey(HttpServletRequest request, String key) {
		Result result = null;
		try {
			//前端需要限定输入的关键字不能为空
			List<Goods> list = goodService.findGoodsByKey(key); 
			
			if(list == null){
				result = new Result(ResultCode.FAIL, "获取对象为空", null);
			}
			else if (list.size() == 0) {
				result = new Result(ResultCode.SUCESS, "找不到与\""+ key +
						"\"相关的商品",list);
			}
			else {
				result = new Result(ResultCode.SUCESS, "获取与\""+ key 
						+"\"相关的商品",list);
			}
			
		}
		catch (UserException e) {
			LOGGER.info(e.getMessage());
			result = new Result(ResultCode.FAIL, "出现异常", null);
		}
		catch (Exception e) {
			result = new Result(ResultCode.FAIL, "出现异常", null);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 添加商品
	 * 
	 * @param request
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/add")
	public Result addGoods(HttpServletRequest request, Goods goods) {
		Result result = null;
		try {
			goodService.addGoods(goods); 
			result = new Result(ResultCode.FAIL, "添加成功", true);
		}
		catch (UserException e) {
			LOGGER.info(e.getMessage());
			result = new Result(ResultCode.FAIL, "添加失败", false);
		}
		catch (Exception e) {
			result = new Result(ResultCode.FAIL, "添加失败", false);
			e.printStackTrace();
		}
		return result;
	}
		
	/**
	 * 删除商品
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public Result deleteGoods(HttpServletRequest request, int id) {
		Result result = null;
		try {
			goodService.deleteGoods(id); 
			result = new Result(ResultCode.FAIL, "删除成功", true);
		}
		catch (Exception e) {
			result = new Result(ResultCode.FAIL, "删除失败", false);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 更新name字段
	 * 
	 * @param request
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/update/name")
	public Result updateName(HttpServletRequest request, Goods goods) {
		Result result = null;
		try {
			goodService.updateName(goods); 
			result = new Result(ResultCode.FAIL, "商品名称更新成功", true);
		}
		catch (UserException e) {
			LOGGER.info(e.getMessage());
			result = new Result(ResultCode.FAIL, "更新失败", false);
		}
		catch (Exception e) {
			result = new Result(ResultCode.FAIL, "更新失败", false);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 更新detail字段
	 * 
	 * @param request
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/update/detail")
	public Result updateDetail(HttpServletRequest request, Goods goods) {
		Result result = null;
		try {
			goodService.updateDetail(goods); 
			result = new Result(ResultCode.FAIL, "商品详情更新成功", true);
		}
		catch (UserException e) {
			LOGGER.info(e.getMessage());
			result = new Result(ResultCode.FAIL, "更新失败", false);
		}
		catch (Exception e) {
			result = new Result(ResultCode.FAIL, "更新失败", false);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 更新price字段
	 * 
	 * @param request
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/update/price")
	public Result updatePrice(HttpServletRequest request, Goods goods) {
		Result result = null;
		try {
			goodService.updatePrice(goods); 
			result = new Result(ResultCode.FAIL, "商品价格更新成功", true);
		}
		catch (UserException e) {
			LOGGER.info(e.getMessage());
			result = new Result(ResultCode.FAIL, "更新失败", false);
		}
		catch (Exception e) {
			result = new Result(ResultCode.FAIL, "更新失败", false);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 更新catalog字段
	 * 
	 * @param request
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/update/catalog")
	public Result updateCatalog(HttpServletRequest request, Goods goods) {
		Result result = null;
		try {
			goodService.updateCatalog(goods); 
			result = new Result(ResultCode.FAIL, "商品分类更新成功", true);
		}
		catch (UserException e) {
			LOGGER.info(e.getMessage());
			result = new Result(ResultCode.FAIL, "更新失败", false);
		}
		catch (Exception e) {
			result = new Result(ResultCode.FAIL, "更新失败", false);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 更新url字段
	 * 
	 * @param request
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/update/url")
	public Result updateUrl(HttpServletRequest request, Goods goods) {
		Result result = null;
		try {
			goodService.updateUrl(goods); 
			result = new Result(ResultCode.FAIL, "商品链接更新成功", true);
		}
		catch (UserException e) {
			LOGGER.info(e.getMessage());
			result = new Result(ResultCode.FAIL, "更新失败", false);
		}
		catch (Exception e) {
			result = new Result(ResultCode.FAIL, "更新失败", false);
			e.printStackTrace();
		}
		return result;
	}
	
    @Test
    public void testEnumGoods(){
    	int i = GoodsCatalog.HOUSEHOLD.value();
        System.out.println(i);
        GoodsCatalog str = GoodsCatalog.valueOf(i);
        System.out.println(str);        
    }
	
}
