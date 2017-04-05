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
	MapUserMapper mapModelMapper;
	
	@Override
	public List<MapUser> getMapUserList(String id)  {
		return mapModelMapper.getMapUserList(id);
	}

	@Override
	public void addMapUser(String id, String ugId, String groupName) throws UserException{
		
		if (StringUtils.isEmpty(groupName)){
			throw new UserException("分组名称不能为空");
		}
		
		MapUser mapModel = new MapUser();
		mapModel.setId(id);
		mapModel.setUgId(ugId);
		mapModel.setGroupName(groupName);
		mapModelMapper.addMapUser(mapModel);
	}

	@Override
	public void deleteMapUser(String id, String ugId) {
		MapUser mapModel = new MapUser();
		mapModel.setId(id);
		mapModel.setUgId(ugId);
		mapModelMapper.deleteMapUser(mapModel);
	}

	@Override
	public void updateMapUserGroupName(String id, String oldName, String newName) {
		mapModelMapper.updateMapUserGroupName(id, oldName, newName);
	}
	@Override
	public void moveMapUser(String id, String ugId, String toGroup) {
		MapUser mapModel = new MapUser();
		mapModel.setId(id);
		mapModel.setUgId(ugId);
		mapModel.setGroupName(toGroup);
		mapModelMapper.moveMapUser(mapModel);
	}
	

}
