package com.fronds.service;

import java.util.List;

import com.fronds.domain.model.TimeMooseStatus;

public interface TimeMooseStatusService {
	
	void createTimeMooseStatus(int userId, String statusText);
	
	void saveTimeMooseStatus(TimeMooseStatus timeMooseStatus);

	List<TimeMooseStatus> getTimeMooseStatusesForUserId(int userId);
	
	List<TimeMooseStatus> getTimeMooseStatusesForMyWall(int userId);

	TimeMooseStatus getTimeMooseStatusById(int statusId);
}
