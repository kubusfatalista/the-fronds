package com.fronds.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fronds.dao.TimeMooseStatusDao;
import com.fronds.domain.model.TimeMooseStatus;
import com.fronds.domain.model.TimeMooseStatus_;

@Repository("timeMooseStatusDao")
public class TimeMooseStatusDaoImpl extends AbstractDao<Integer, TimeMooseStatus> implements TimeMooseStatusDao {

	@Override
	public void saveTimeMooseStatus(TimeMooseStatus timeMooseStatus) {
		save(timeMooseStatus);
	}

	@Override
	public List<TimeMooseStatus> getTimeMooseStatusesForUserId(int userId) {
		CriteriaQuery<TimeMooseStatus> criteria = getCriteriaBuilder().createQuery(TimeMooseStatus.class);
		Root<TimeMooseStatus> root = criteria.from(TimeMooseStatus.class);
		criteria.select(root);
		criteria.orderBy(getCriteriaBuilder().desc(root.get(TimeMooseStatus_.creationDate)));
		criteria.where(getCriteriaBuilder().equal(root.get(TimeMooseStatus_.user), userId));

		return getSession().createQuery(criteria).getResultList();
	}

	@Override
	public TimeMooseStatus getTimeMooseStatusById(int id) {
		return getByKey(id);
	}
}
