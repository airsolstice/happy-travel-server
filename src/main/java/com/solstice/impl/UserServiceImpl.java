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
	public void findUserById(String id) throws UserException {
		User user = userMapper.findUserById(id);
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
	public void loginOut(String id) throws UserException {
		User user = new User(id, 0);
		userMapper.updateStatus(user);
	}

	public User login(User user) throws UserException {
		// 根据账号查找用户
		User _user = userMapper.findUserById(user.getId());

		// 账号不存在
		if (null == _user) {
			throw new UserException("账号不存在");
		}

		// 账号与密码不匹配
		if (!_user.getPwd().equals(user.getPwd())) {
			throw new UserException("账号或密码错误");
		}
		user.setStatus(1);
		userMapper.updateStatus(user);
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
	
	public void updatePwd(String id, String pwd) throws UserException {
		User user = userMapper.findUserById(id);
		if (!Utils.isEmpty(user)) {
			user.setPwd(pwd);
			userMapper.updatePwd(user);
		} else
			throw new UserException("该id不存在");
	}

	@Override
	public void updatePwdByPhone(String phone, String pwd) throws UserException {
		User user = userMapper.findUserByPhone(phone);
		if (!Utils.isEmpty(user)) {
			user.setPwd(pwd);
			userMapper.updatePwdByPhone(user);
		} else
			throw new UserException("该手机号码未注册");		
	}

	@Override
	public void updatePwdByEmail(String email, String pwd) throws UserException {
		User user = userMapper.findUserByEmail(email);
		if (!Utils.isEmpty(user)) {
			user.setPwd(pwd);
			userMapper.updatePwdByPhone(user);
		} else
			throw new UserException("该邮箱未注册");	
	}

	@Override
	public boolean isExist(String id) {
		User user = userMapper.findUserById(id);
		if(user != null){
			return true;
		}
		return false;
	}

}
