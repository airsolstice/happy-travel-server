package com.solstice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.solstice.bean.MapUser;
import com.solstice.exception.UserException;
import com.solstice.mapper.MapUserMapper;
import com.solstice.service.MapUserService;

@Service
public class MapUserServiceImpl implements MapUserService {
	@Autowired
	MapUserMapper mapUserMapper;
	
	@Override
	public List<MapUser> getMapUserList(String id)  {
		return mapUserMapper.getMapUserList(id);
	}

	@Override
	public void addMapUser(String id, String ugId, String groupName) throws UserException{
		
		if (StringUtils.isEmpty(groupName)){
			throw new UserException("分组名称不能为空");
		}
		
		MapUser user = new MapUser();
		user.setId(id);
		user.setUgId(ugId);
		user.setGroupName(groupName);
		mapUserMapper.addMapUser(user);
	}

	@Override
	public void deleteMapUser(String id, String ugId) {
		MapUser user = new MapUser();
		user.setId(id);
		user.setUgId(ugId);
		mapUserMapper.deleteMapUser(user);
	}

	@Override
	public void updateMapUserGroupName(String id, String oldName, String newName) {
		mapUserMapper.updateMapUserGroupName(id, oldName, newName);
	}
	@Override
	public void moveMapUser(String id, String ugId, String toGroup) {
		MapUser user = new MapUser();
		user.setId(id);
		user.setUgId(ugId);
		user.setGroupName(toGroup);
		mapUserMapper.moveMapUser(user);
	}
	

}
