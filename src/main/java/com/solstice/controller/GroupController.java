package com.solstice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solstice.bean.Group;
import com.solstice.bean.Result;
import com.solstice.bean.ResultCode;
import com.solstice.exception.UserException;
import com.solstice.service.GroupService;
import com.solstice.service.UserService;

@Controller
@RequestMapping(value = "/group")
public class GroupController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/list", produces = "application/json; charset=utf-8")
	public String getGroupList(HttpServletRequest request, String id) {
		Result result = null;
		try {
			if(!userService.isExist(id)){
				throw new UserException("手机帐号不存在");
			}
			List<Group> models = (ArrayList<Group>) groupService.getGroupListById(id);
			result = new Result(ResultCode.SUCESS, "获取好友列表", models);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "获取失败", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "获取失败", e.getMessage());
			e.printStackTrace();
		}
		System.out.println(result.toString());
		return result.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/add", produces = "application/json; charset=utf-8")
	public String addUser(HttpServletRequest request, String id, String fid, String gname) {
		Result result = null;
		try {
			if(!userService.isExist(id) || !userService.isExist(fid)){
				throw new UserException("帐号不存在");
			}
			userService.findUserById(fid);
			Group group = new Group(id, fid, gname);
			groupService.addUserToGroup(group);
			result = new Result(ResultCode.SUCESS, "添加成功", null);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "获取失败", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "添加失败", e.getMessage());
			e.printStackTrace();
		}
		return result.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/delete", produces = "application/json; charset=utf-8")
	public String delteteUserFromGroup(HttpServletRequest request, String id, String fid) {
		Result result = null;
		try {
			if(!userService.isExist(id) || !userService.isExist(fid)){
				throw new UserException("帐号不存在");
			}
			Group group = new Group(id, fid);
			groupService.deleteUserFromGroup(group);
			result = new Result(ResultCode.SUCESS, "删除成功", null);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "删除失败", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "删除失败", e.getMessage());
			e.printStackTrace();
		}
		return result.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/update", produces = "application/json; charset=utf-8")
	public String updateGroupName(HttpServletRequest request, String id, String oldName, String newName) {
		Result result = null;
		try {
			if(!userService.isExist(id)){
				throw new UserException("手机帐号不存在");
			}
			if (oldName.equals(newName)) {
				throw new UserException("新组名和旧组名一致，无需修改");
			}
			groupService.updateGroupName(id, oldName, newName);
			result = new Result(ResultCode.SUCESS, "更新成功", null);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "更新失败", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "更新失败", e.getMessage());
			e.printStackTrace();
		}
		return result.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/move", produces = "application/json; charset=utf-8")
	public String moveMapUser(HttpServletRequest request, String id, String fid, String gname) {
		Result result = null;
		try {
			if(!userService.isExist(id) || !userService.isExist(fid)){
				throw new UserException("帐号不存在");
			}
			Group group = new Group(id, fid, gname);
			groupService.moveUser(group);
			result = new Result(ResultCode.SUCESS, "移动成功", null);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "移动失败", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "移动失败", e.getMessage());
			e.printStackTrace();
		}
		return result.toString();
	}
}
