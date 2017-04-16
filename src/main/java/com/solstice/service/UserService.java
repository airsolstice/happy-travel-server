package com.solstice.service;

import com.solstice.bean.User;
import com.solstice.exception.UserException;

public interface UserService {
	public boolean isExist(String id);
	public void findUserById(String id) throws UserException;
	public void findUserByUserPhone(String phone) throws UserException;
	public void findUserByEmail(String email) throws UserException;
	public void addUser(User user)throws UserException;
	public String findIdByCode(String activeCode)throws UserException;
	public void loginOut(String id)throws UserException;
	public User login(User user)throws UserException;
	public void updatePwd(String id,String pwd)throws UserException;
	public void updatePwdByPhone(String phone,String pwd)throws UserException;
	public void updatePwdByEmail(String email,String pwd)throws UserException;
	public void checkEmail(String email) throws UserException;
	public void checkPhone(String phone) throws UserException;
}