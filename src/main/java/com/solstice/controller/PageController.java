package com.solstice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
}
