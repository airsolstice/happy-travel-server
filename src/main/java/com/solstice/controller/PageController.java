package com.solstice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.solstice.bean.Result;
import com.solstice.bean.ResultCode;
import com.solstice.utils.FileUpLoadUtil;

@Controller
public class PageController {

	@RequestMapping("loginPage")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping("registPage")
	public ModelAndView regist() {
		ModelAndView mav = new ModelAndView("regist");
		return mav;
	}
	
	@RequestMapping("forgetpwdPage")
	public ModelAndView forgetpwd() {
		ModelAndView mav = new ModelAndView("forgetpwd");
		return mav;
	}
	
	@RequestMapping("")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}	

	@RequestMapping("choose")
	public ModelAndView choose() {
		ModelAndView mav = new ModelAndView("choose");
		return mav;
	}	

	@ResponseBody
	@RequestMapping(value = "/upload", produces = "application/json; charset=utf-8")
	public String upload(HttpServletRequest request) throws Exception{
		
		Result result = null;
		List<String> path = FileUpLoadUtil.upload(request);
		System.out.println(path);
		if(path.size() != 0){
			result = new Result(ResultCode.SUCESS, "上传成功", path.get(0));
		} else {
			result = new Result(ResultCode.FAIL, "异常");
		}
		return result.toString();
	}
	
}
