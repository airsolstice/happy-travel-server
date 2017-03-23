package com.solstice.mapper;

import java.util.List;

import com.solstice.bean.MapModel;

public interface MapModelMapper {
	public List<MapModel> getMapModelList(int id);
	//在添加之前必须判断这个好友是否已经存在
	public void addMapModel(MapModel mapModel);
	public void deleteMapModel(MapModel mapModel);
	//在更新组名之前须判断这个组名是否已经被使用
	public void updateMapModelGroupName(int id,String oldName,String newName);
	public void moveMapModel(MapModel mapModel);

}
