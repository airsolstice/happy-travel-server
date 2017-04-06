package com.solstice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solstice.bean.Result;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

	@RequestMapping(value = "/list")
	public String getMapUserList(HttpServletRequest request, String id) {
		Result result = null;
		return "message";
	}



}
