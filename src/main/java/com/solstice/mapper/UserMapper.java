package com.solstice.mapper;

import com.solstice.bean.User;

public interface UserMapper {
	public User findUserByEmail(String email);
	public User findUserByUserName(String name);
	public void addUser(User user);
	public String findIdByCode(String activeCode);
	public void active(String id);
	public void updatePwd(User user);
}
