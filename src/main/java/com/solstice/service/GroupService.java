package com.solstice.service;

import java.util.List;

import com.solstice.bean.Group;
import com.solstice.exception.UserException;

public interface GroupService {
	public List<Group> getGroupListById(String id) throws UserException;
	public void addUserToGroup(Group group) throws UserException;
	public void deleteUserFromGroup(Group group) throws UserException;
	public void updateGroupName(String id,String oldName,String newName) throws UserException;
	public void moveUser(Group group) throws UserException;
}
