package com.solstice.service;

import java.util.List;

import com.solstice.bean.MapUser;
import com.solstice.exception.UserException;

public interface MapUserService {
	public List<MapUser> getMapUserList(String id);
	public void addMapUser(String id, String ugId, String groupName) throws UserException;
	public void deleteMapUser(String id, String ugId);
	public void updateMapUserGroupName(String id,String oldName,String newName);
	public void moveMapUser(String id,String ugId,String toGroup);
}
