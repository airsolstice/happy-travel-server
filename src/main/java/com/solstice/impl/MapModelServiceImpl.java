package com.solstice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.solstice.bean.MapModel;
import com.solstice.exception.UserException;
import com.solstice.mapper.MapModelMapper;
import com.solstice.service.MapModelService;

@Service
public class MapModelServiceImpl implements MapModelService {
	@Autowired
	MapModelMapper mapModelMapper;
	
	@Override
	public List<MapModel> getMapModelList(int id)  {
		return mapModelMapper.getMapModelList(id);
	}

	@Override
	public void addMapModel(int id, int ugId, String groupName) throws UserException{
		
		if(id < 0 ){
			throw new UserException(String.format("id[%s]参数必须为正整数", id));
		} 
		else if (ugId < 0 ){
			throw new UserException(String.format("contactId[%s]参数必须为正整数", ugId));
		}
		else if (StringUtils.isEmpty(groupName)){
			throw new UserException("分组名称不能为空");
		}
		
		MapModel mapModel = new MapModel();
		mapModel.setId(id);
		mapModel.setUgId(ugId);
		mapModel.setGroupName(groupName);
		mapModelMapper.addMapModel(mapModel);
	}

	@Override
	public void deleteMapModel(int id, int ugId) {
		MapModel mapModel = new MapModel();
		mapModel.setId(id);
		mapModel.setUgId(ugId);
		mapModelMapper.deleteMapModel(mapModel);
	}

	@Override
	public void updateMapModelGroupName(int id, String oldName, String newName) {
		mapModelMapper.updateMapModelGroupName(id, oldName, newName);
	}
	@Override
	public void moveMapModel(int id, int ugId, String toGroup) {
		MapModel mapModel = new MapModel();
		mapModel.setId(id);
		mapModel.setUgId(ugId);
		mapModel.setGroupName(toGroup);
		mapModelMapper.moveMapModel(mapModel);
	}
	

}
