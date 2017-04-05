package com.solstice.mapper;

import java.util.List;

import com.solstice.bean.MapUser;

public interface MapUserMapper {
	public List<MapUser> getMapUserList(String id);
	//在添加之前必须判断这个好友是否已经存在
	public void addMapUser(MapUser mapUser);
	public void deleteMapUser(MapUser mapUser);
	//在更新组名之前须判断这个组名是否已经被使用
	public void updateMapUserGroupName(String id,String oldName,String newName);
	public void moveMapUser(MapUser mapUser);
}
