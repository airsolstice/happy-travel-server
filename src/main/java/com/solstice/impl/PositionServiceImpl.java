package com.solstice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solstice.bean.Position;
import com.solstice.exception.UserException;
import com.solstice.mapper.PositionMapper;
import com.solstice.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	PositionMapper positionMapper;
	
	@Override
	public List<Position> getUserTrace(String id) throws UserException{
		List<Position> result = positionMapper.getPositionTraceById(id);
		if(result == null){
			throw new UserException("数据获取失败");
		}
		return result;
	}

	@Override
	public Position getUserPosition(String id) throws UserException{
		Position position = positionMapper.getLastPositionById(id);
		if(position == null){
			throw new UserException("数据获取失败");
		}
		return position;
	}

	@Override
	public void updatePosition(Position position) throws UserException{
		positionMapper.updatePosition(position);
	}
}
