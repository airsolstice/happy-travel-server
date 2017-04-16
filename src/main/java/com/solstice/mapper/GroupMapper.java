package com.solstice.mapper;

import java.util.List;

import com.solstice.bean.Group;

public interface GroupMapper {
	public List<Group> getGroupListById(String id);
	public void addUser(Group group);
	public Group findGroupUser(Group group);
	public void deleteUserFromGroup(Group group);
	public void updateGroupName(String id,String oldName,String newName);
	public void moveUser(Group group);
	public String getGroupName(Group group);
}
