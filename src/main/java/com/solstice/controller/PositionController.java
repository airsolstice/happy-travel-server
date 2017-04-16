package com.solstice.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solstice.bean.Position;
import com.solstice.bean.Result;
import com.solstice.bean.ResultCode;
import com.solstice.exception.UserException;
import com.solstice.service.PositionService;
import com.solstice.service.UserService;

@Controller
@RequestMapping(value = "/loc")
public class PositionController {

	@Autowired
	PositionService positionService;
	
	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/trace" )
	public String getUserTrace(HttpServletRequest request, String id) {
		Result result = null;
		System.out.println(id);
		try {
			if(!userService.isExist(id)){
				throw new UserException("手机帐号不存在");
			}
			List<Position> models =  positionService.getUserTrace(id);
			result = new Result(ResultCode.SUCESS, "获取用户轨迹列表", models);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "获取失败", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "获取失败", e.getMessage());
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/position", produces = "application/json; charset=utf-8")
	public String getUserPosition(HttpServletRequest request, String id) {
		Result result = null;
		try {
			if(!userService.isExist(id)){
				throw new UserException("手机帐号不存在");
			}
			Position position =  positionService.getUserPosition(id);
			result = new Result(ResultCode.SUCESS, "获取用户当前位置", position);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "获取失败", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "获取失败", e.getMessage());
			e.printStackTrace();
		}
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", produces = "application/json; charset=utf-8")
	public String updateUserPosition(HttpServletRequest request, Position position) {
		Result result = null;
		try {
			if(!userService.isExist(position.getId())){
				throw new UserException("手机帐号不存在");
			}
	
			position.setTime(new Date());
			System.out.println(position.toString());
			positionService.updatePosition(position);
			result = new Result(ResultCode.SUCESS, "更新用户当前位置", position);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "更新失败", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "更新失败", e.getMessage());
			e.printStackTrace();
		}
		System.out.println(result.toString());
		
		return result.toString();
	}
	
	
}
