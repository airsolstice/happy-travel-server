package com.solstice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solstice.bean.MapModel;
import com.solstice.bean.Result;
import com.solstice.bean.ResultCode;
import com.solstice.service.MapModelService;

@Controller
@RequestMapping(value = "/map")
public class MapModelController {
	@Autowired
	private MapModelService mapModelService;

	@RequestMapping(value = "/list")
	public String getMapModelList(HttpServletRequest request, int id) {
		Result result = null;
		try {
			List<MapModel> models = (ArrayList<MapModel>) mapModelService
					.getMapModelList(id);
			result = new Result(ResultCode.SUCESS, "get contact success",
					models);
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "get contact faild", null);
			e.printStackTrace();
		}

		request.setAttribute("data", result.toString());
		return "contact";
	}

	@RequestMapping(value = "/contact")
	public String Contact() {
		return "contact";
	}

	@RequestMapping(value = "/add")
	public String addModel(HttpServletRequest request, int id, int contactId,
			String groupName) {
		Result result = null;
		try {
			String name = new String(groupName.getBytes("gbk"), "utf-8");
			mapModelService.addMapModel(id, contactId, name);
			result = new Result(ResultCode.SUCESS, "add success", null);
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "add faild", null);
			e.printStackTrace();
		}

		request.setAttribute("data", result.toString());
		return "contact";
	}

	@RequestMapping(value = "/delete")
	public String delteteMapUser(HttpServletRequest request, int id,
			int contactId) {
		Result result = null;
		try {
			mapModelService.deleteMapModel(id, contactId);
			result = new Result(ResultCode.SUCESS, "delete success", null);
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "delete faild", null);
			e.printStackTrace();
		}
		request.setAttribute("data", result.toString());
		return "contact";
	}

	@RequestMapping(value = "/update")
	public String updateMapUserGroupName(HttpServletRequest request, int id,
			int contactId) {
		Result result = null;
		try {
			result = new Result(ResultCode.SUCESS, "update success", null);
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "update faild", null);
			e.printStackTrace();
		}

		request.setAttribute("data", result.toString());
		return "contact";

	}

	@RequestMapping(value = "/move")
	public String moveMapUser(HttpServletRequest request, int id,
			int contactId, String toGroup) {
		Result result = null;
		try {
			mapModelService.moveMapModel(id, contactId, toGroup);
			result = new Result(ResultCode.SUCESS, "move success", null);
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "move faild", null);
			e.printStackTrace();
		}

		request.setAttribute("data", result.toString());
		return "contact";
	}
}
