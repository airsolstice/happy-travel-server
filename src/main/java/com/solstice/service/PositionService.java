package com.solstice.service;

import java.util.List;

import com.solstice.bean.Position;
import com.solstice.exception.UserException;

public interface PositionService {

	public List<Position> getUserTrace(String id) throws UserException;
	
	public Position getUserPosition(String id) throws UserException;
	
	public void updatePosition(Position position) throws UserException;
}
