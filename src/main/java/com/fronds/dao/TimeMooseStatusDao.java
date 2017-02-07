package com.fronds.dao;

import java.util.List;

import com.fronds.domain.model.TimeMooseStatus;

public interface TimeMooseStatusDao {
    public void saveTimeMooseStatus(TimeMooseStatus timeMooseStatus);
    public List<TimeMooseStatus> getTimeMooseStatusesForUserId(int userId);
    public TimeMooseStatus getTimeMooseStatusById(int statusId);

}
