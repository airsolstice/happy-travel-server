package com.solstice.service;

import java.util.List;

import com.solstice.bean.MapModel;
import com.solstice.exception.UserException;

public interface MapModelService {
	public List<MapModel> getMapModelList(int id);
	public void addMapModel(int id, int contactId, String groupName) throws UserException;
	public void deleteMapModel(int id, int contactId);
	public void updateMapModelGroupName(int id,String oldName,String newName);
	public void moveMapModel(int id,int contactId,String toGroup);
}
