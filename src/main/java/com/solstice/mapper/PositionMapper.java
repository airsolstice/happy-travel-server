package com.solstice.mapper;

import java.util.List;
import com.solstice.bean.Position;

public interface PositionMapper {
	public List<Position> getPositionTraceById(String id);
	public Position getLastPositionById(String id);
	public void updatePosition(Position position);
}
