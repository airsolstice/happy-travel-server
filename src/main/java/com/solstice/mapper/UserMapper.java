package com.solstice.mapper;

import com.solstice.bean.User;

public interface UserMapper {
	public User findUserByEmail(String email);
	public User findUserByPhone(String phone);
	public User findUserById(String id);
	public void addUser(User user);
	public String findIdByCode(String activeCode);
	public void updateStatus(User user);
	public void updatePwd(User user);
	public void updatePwdByPhone(User user);
	public void updatePwdByEmail(User user);
	public int getChatIdById(String id);
	public User findUserByChatId(int chatId);
}
