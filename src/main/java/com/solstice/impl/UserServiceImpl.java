package com.solstice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solstice.bean.User;
import com.solstice.exception.UserException;
import com.solstice.mapper.UserMapper;
import com.solstice.service.UserService;
import com.solstice.utils.Utils;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	/**
	 * 根据帐号查找用户
	 */
	@Override
	public void findUserByUserId(String id) throws UserException {
		User user = userMapper.findUserByUserId(id);
		if (user != null) {
			throw new UserException("该帐号已经被注册");
		}
	}
	
	/**
	 * 根据手机号查找用户
	 */
	@Override
	public void findUserByUserPhone(String phone) throws UserException {
		User user = userMapper.findUserByPhone(phone);
		if (user != null) {
			throw new UserException("该手机号已经被注册");
		}
	}
	
	/**
	 * 根据邮箱查找账号
	 */

	public void findUserByEmail(String email) throws UserException {
		User user = userMapper.findUserByEmail(email);
		if (user != null) {
			throw new UserException("该邮箱已经被注册");
		}
	}

	/**
	 * 添加用户
	 */
	public void addUser(User user) {
		// MD5加密密码
		user.setPwd(Utils.MD5(user.getPwd()));
		userMapper.addUser(user);
	}

	/**
	 * 根据激活码查找id
	 */
	public String findIdByCode(String activeCode) throws UserException {
		String id = userMapper.findIdByCode(activeCode);
		if (id == null)
			throw new UserException("激活码已失效，请重新注册");
		return id;
	}

	/**
	 * 根据id激活账号
	 */
	public void active(String id) throws UserException {
		userMapper.active(id);
	}

	public User login(User user) throws UserException {
		// 根据账号查找用户
		User _user = userMapper.findUserByUserId(user.getId());

		// 账号不存在
		if (null == _user) {
			throw new UserException("账号不存在");
		}
		// 账号停用
		if (_user.getStatus() == 0) {
			throw new UserException("账号未激活");
		}
		// 账号与密码不匹配
		if (!_user.getPwd().equals(Utils.MD5(user.getPwd()))) {
			throw new UserException("账号或密码错误");
		}
		return _user;
	}

	public void checkEmail(String email) throws UserException{
		User user = userMapper.findUserByEmail(email);
		if(Utils.isEmpty(user)){
			throw new UserException("该邮箱未注册");
		}
	}

	public void checkPhone(String phone) throws UserException{
		User user = userMapper.findUserByPhone(phone);
		if(Utils.isEmpty(user)){
			throw new UserException("该手机号未注册");
		}
	}
	
	public void updatePwd(String email, String pwd) throws UserException {
		User user = userMapper.findUserByEmail(email);
		if (!Utils.isEmpty(user)) {
			user.setPwd(pwd);
			userMapper.updatePwd(user);
		} else
			throw new UserException("该邮箱未注册");
	}




}
