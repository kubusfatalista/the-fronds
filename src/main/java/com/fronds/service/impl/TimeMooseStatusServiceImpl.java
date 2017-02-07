package com.fronds.service.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronds.dao.TimeMooseStatusDao;
import com.fronds.domain.model.TimeMooseStatus;
import com.fronds.domain.model.User;
import com.fronds.service.TimeMooseStatusService;
import com.fronds.service.UserService;

@Service("timeMooseStatusService")
@Transactional
public class TimeMooseStatusServiceImpl implements TimeMooseStatusService {
	
	@Autowired
	private TimeMooseStatusDao timeMooseStatusDao;
	
	@Autowired
	private UserService userService;

	@Override
	public void saveTimeMooseStatus(TimeMooseStatus timeMooseStatus) {
		timeMooseStatusDao.saveTimeMooseStatus(timeMooseStatus);
	}
	
	@Override
	public void createTimeMooseStatus(int userId, String statusText) {
		User user = userService.getUserById(userId);
		TimeMooseStatus timeMooseStatus = new TimeMooseStatus();
		timeMooseStatus.setUser(user);
		timeMooseStatus.setLatitude(-1);
		timeMooseStatus.setLongitude(-1);
		timeMooseStatus.setReactions(new HashSet<>());
		timeMooseStatus.setComments(new ArrayList<>());
		timeMooseStatus.setText(statusText);
		saveTimeMooseStatus(timeMooseStatus);
	}

	@Override
	public List<TimeMooseStatus> getTimeMooseStatusesForUserId(int userId) {
		return timeMooseStatusDao.getTimeMooseStatusesForUserId(userId);
	}

	@Override
	public TimeMooseStatus getTimeMooseStatusById(int statusId) {
		return timeMooseStatusDao.getTimeMooseStatusById(statusId);
	}



}
