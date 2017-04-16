package com.solstice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solstice.bean.Group;
import com.solstice.exception.UserException;
import com.solstice.mapper.GroupMapper;
import com.solstice.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	GroupMapper groupMapper;

	@Override
	public List<Group> getGroupListById(String id) throws UserException {
		List<Group> result = groupMapper.getGroupListById(id);
		if(result == null){
			throw new UserException("获取好友列表失败");
		}
		return result;
	}

	@Override
	public void addUserToGroup(Group group) throws UserException {
		groupMapper.addUser(group);
		Group result = groupMapper.findGroupUser(group);
		
		if(result == null){
			throw new UserException("添加异常");
		}
	}

	@Override
	public void deleteUserFromGroup(Group group) throws UserException{
		groupMapper.deleteUserFromGroup(group);
		Group result = groupMapper.findGroupUser(group);
		
		if(result != null){
			throw new UserException("删除异常");
		}
	}

	@Override
	public void updateGroupName(String id, String oldName, String newName) {
		groupMapper.updateGroupName(id, oldName, newName);
	}

	@Override
	public void moveUser(Group group) throws UserException{
		String gname = groupMapper.getGroupName(group);
		
		if(gname == null){
			throw new UserException("查询异常");
		}
		
		if(gname.equals(group.getGroupName())){
			throw new UserException("已经在当前分组，无需移动");
		}
		
		groupMapper.moveUser(group);
	}


}
