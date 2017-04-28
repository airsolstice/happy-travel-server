package com.solstice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solstice.bean.Result;
import com.solstice.bean.ResultCode;
import com.solstice.bean.User;
import com.solstice.exception.UserException;
import com.solstice.service.UserService;
import com.solstice.utils.MailUtil;
import com.solstice.utils.Utils;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 修改密码
	 * @param request
	 * @param id
	 * @param pwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/retrievePwd", produces = "application/json; charset=utf-8")
	public String retrievePwd(HttpServletRequest request, String id, String pwd) {
		Result result = null;
		Map<String, String> errors = new HashMap<String, String>();
		// 用户名的判断,可以是手机号或者邮箱
		if (Utils.isEmpty(id)) {
			errors.put("id", "用户名不能为null");
		} else if (!Utils.isPhone(id) && ! Utils.isEmail(id)){
			errors.put("id", "id只能为者手机号或者邮箱");
		}
		
		// 密码的判断
		if (Utils.isEmpty(pwd)) {
			errors.put("pwd", "密码不能为null");
		}
		
		if (errors.size() > 0) {
			// 保存错误信息
			request.setAttribute("errors", errors);
			result = new Result(ResultCode.FAIL, "表单验证失败", errors.toString());
			// 保存用户信息
			request.setAttribute("id", id);
			request.setAttribute("pwd", pwd);
			return result.toString();
		}
		
		try {
			
			if(Utils.isEmail(id)){
				userService.updatePwd(id, pwd);
				result = new Result(ResultCode.SUCESS, "成功通过邮箱修改密码");
			} else if (Utils.isPhone(id)){
				userService.updatePwd(id, pwd);
				result = new Result(ResultCode.SUCESS, "成功手机号修改密码");
			}
			
			return result.toString();
		} catch (UserException e) {
			request.setAttribute("error", e.getMessage());
			result = new Result(ResultCode.FAIL, "密码修改失败",e.getMessage());
			return result.toString();
		}
	}

	
	/**
	 * 登入
	 * @param request
	 * @param user
	 * @return
	 */
	@ResponseBody 
	@RequestMapping(value = "/login", produces = "application/json; charset=utf-8")
	public String login(HttpServletRequest request, User user) {
		Result result = null;
		Map<String, String> errors = new HashMap<String, String>();
		// 用户名的判断,可以是手机号或者邮箱
		if (Utils.isEmpty(user.getId())) {
			errors.put("id", "用户名不能为null");
		} else if (!Utils.isPhone(user.getId()) && !Utils.isEmail(user.getId())){
			errors.put("id", "id只能为者手机号或者邮箱");
		}
		
		// 密码的判断
		if (Utils.isEmpty(user.getPwd())) {
			errors.put("pwd", "密码不能为null");
		}

		if (errors.size() > 0) {
			// 保存错误信息
			request.setAttribute("errors", errors);
			result = new Result(ResultCode.FAIL, "表单验证失败", errors.toString());
			// 保存用户信息
			request.setAttribute("user", user);
			return result.toString();
		}
		try {
			//改变状态
			user.setStatus(1);
			User _user = userService.login(user);
			
			result = new Result(ResultCode.SUCESS, "登入成功", _user.toString());
			// 保存账号信息到session域中
			request.getSession().setAttribute("user", _user);
		} catch (UserException e) {
			request.setAttribute("error", e.getMessage());
			result = new Result(ResultCode.FAIL, "登入异常", e.getMessage());
			request.setAttribute("user", user);
		}
		System.out.println(result.toString());
		return result.toString();
	}
	
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/regist", produces = "application/json; charset=utf-8")
	public String regist(User user) {
		Result result = null;
		//默认头像
		user.setUrl("http://img1.imgtn.bdimg.com/it/u=3995083595,771039139&fm=214&gp=0.jpg");
		//根据id的类别，覆盖相应的属性
		String id = user.getId();
		if (!Utils.isEmpty(id)){
			if (Utils.isEmail(id)){
				user.setEmail(id);
			}
			else if (Utils.isPhone(id)){
				user.setPhone(id);
			}
		}
	
		//验证属性的合法性
		Map<String, String> errors = verifyInput(user);		
		if (errors.size() > 0) {
			// 保存错误信息
			result = new Result(ResultCode.FAIL, "注册失败", errors.toString());
			return result.toString();
		}
		
		try {
			// 调用service，验证手机号和邮箱是否已经被注册
			userService.findUserById(user.getId());
			userService.findUserByEmail(user.getEmail());
			userService.findUserByUserPhone(user.getPhone());
			//添加用户
			userService.addUser(user);
			result = new Result(ResultCode.SUCESS, "注册成功", null);
		}
		catch (UserException e) {
			e.printStackTrace();
			// 保存错误信息
			result = new Result(ResultCode.FAIL, e.getMessage(), e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody 
	@RequestMapping(value = "/info/get", produces = "application/json; charset=utf-8")
	public String getUserInfo(HttpServletRequest request, String id) {
		Result result = null;	
		try {
			// 调用service，验证手机号和邮箱是否已经被注册
			User user = userService.getUserInfo(id);
			result = new Result(ResultCode.SUCESS, "用户信息获取成功", user.toString());
		}
		catch (UserException e) {
			e.printStackTrace();
			// 保存错误信息
			result = new Result(ResultCode.FAIL, e.getMessage(), e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result.toString());
		return result.toString();
	}
	
	/**
	 * 获取chat id
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody 
	@RequestMapping(value = "/chatid/get", produces = "application/json; charset=utf-8")
	public String getChatId(HttpServletRequest request, String id) {
		Result result = null;
		try {
			int chatId = userService.getChatId(id);
			result = new Result(ResultCode.SUCESS, "chat id获取成功", chatId);
		} catch (UserException e) {
			e.printStackTrace();
			result = new Result(ResultCode.SUCESS, "异常", e.getMessage());
		}
		return result.toString();
	}
	
	/**
	 * 通过chat id获取用户信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody 
	@RequestMapping(value = "/info/bychatid/get", produces = "application/json; charset=utf-8")
	public String getUserInfoByChatId(HttpServletRequest request, int chatId) {
		Result result = null;
		try {
			User user= userService.getUserInfoByChatId(chatId);
			result = new Result(ResultCode.SUCESS, "用户信息获取成功", user.toString());
		} catch (UserException e) {
			e.printStackTrace();
			result = new Result(ResultCode.SUCESS, "异常", e.getMessage());
		}
		return result.toString();
	}
	
	/**
	 * 注销 未测试
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/loginOut")
	public String loginOut(HttpServletRequest request, String id) {
		Result result = null;
		try {
			//验证帐号是否存在
			userService.findUserById(id);
			//改变状态
			userService.loginOut(id);
			result = new Result(ResultCode.SUCESS, "注销成功");
		} catch (UserException e) {
			e.printStackTrace();
			result = new Result(ResultCode.SUCESS, "注销异常", e.getMessage());
		}
		return result.toString();
	}
	
	
	
	/**
	 * 验证输入合法性
	 * @param user
	 * @return map
	 */
	public Map<String, String> verifyInput(User user) {

		Map<String, String> errors = new HashMap<String, String>();
		// 帐号的判断：可以是手机号，或者邮箱
		if (Utils.isEmpty(user.getId())) {
			errors.put("id", "用户名不能为null");
		} else if (!Utils.isEmail(user.getId()) && !Utils.isPhone(user.getId())){
			errors.put("id", "id只能为邮箱或者手机号");
		}

		// 密码的判断
		if (Utils.isEmpty(user.getPwd())) {
			errors.put("pwd", "密码不能为null");
		} 

		// 邮箱的判断
		if (Utils.isEmpty(user.getEmail())) {
			errors.put("email", "邮箱不能为null");
		}
		// 邮箱格式为***@**.***
		else if (!Utils.isEmail(user.getEmail().trim())) {
			errors.put("email", "邮箱格式不正确");
		}
		// 手机号的判断
		if (Utils.isEmpty(user.getPhone())) {
			errors.put("phone", "手机号码不能为null");
		}
		else if (!Utils.isPhone(user.getPhone().trim())) {
			errors.put("phone", "手机号码格式不正确");
		}
		
		return errors;
	}

	
	
	@RequestMapping("/toRegist")
	public String toRegist() {
		return "regist";
	}

	/*
	 * 激活账号 根据激活码查找id 将查找到的id设置其status为1(激活状态)
	 */
	@RequestMapping("/active")
	public String active(String id) {
		try {
			//验证帐号是否存在
			userService.findUserById(id);
			//改变激活状态
			//userService.active(id);
		} catch (UserException e) {
			// 激活失败处理
			e.printStackTrace();
		}
		return "login";
	}

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}


	/*
	 * 去找回密码
	 */
	@RequestMapping("/toforgetpwd")
	public String toforgetpwd() {
		return "forgetpwd";
	}

	@RequestMapping("/forgetpwd")
	public String forgetpwd(HttpServletRequest request, String email, String pwd) {
		Map<String, String> errors = new HashMap<String, String>();
		
		if (Utils.isEmpty(email)) {
			errors.put("email", "邮箱不能为null");
		} else if (!Utils.isEmail(email)) {
			errors.put("email", "邮箱格式不正确");
		}

		try {
			userService.checkEmail(email);
		} catch (UserException e) {
			errors.put("email", e.getMessage());
		}

		// 密码的判断
		if (Utils.isEmpty(pwd)) {
			errors.put("pwd", "密码不能为null");
		} else if (pwd.trim().length() > 18 || pwd.trim().length() < 6) {
			errors.put("pwd", "密码长度必须介于6~18之间");
		}
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("email", email);
			return "forgetpwd";
		}
		String text = "你正在使用该邮箱找回密码，请点击下面的链接完成密码找回；如不是本人操作，请忽略" + System.getProperty("line.separator", "\n")
				+ "<a href=\"http://localhost:8080" + request.getContextPath() + "/user/retrievePwd?email=" + email
				+ "&pwd=" + Utils.MD5(pwd) + "\"></a>";
		try {
			MailUtil.sendMail("找回密码", text, new String[] { email });
		} catch (Exception e) {
			// 出错处理
			e.printStackTrace();
		}
		// 发送了消息之后应该转到一个信息提示页面，提示已经提交，请到邮箱去找回密码
		request.setAttribute("message",
				"请到邮箱" + email + "完成密码找回操作。如已操作请点击下面的链接去登录" + System.getProperty("line.separator")
						+ "<a href=\"http://localhost:8080" + request.getContextPath() + "/user/toLogin");
		return "message";
	}


	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	

	
}
