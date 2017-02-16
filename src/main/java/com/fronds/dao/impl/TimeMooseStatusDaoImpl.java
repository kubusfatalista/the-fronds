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
	public TimeMooseStatus getTimeMooseStatusById(int id) {
		return getByKey(id);
	}
	
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


	/**
	 *  uzywajac criteria query nie da sie zrobic joina jak nie mamy wszystkiego pomapowanego
	 *  czyli jak chce polaczyc tabele normalnie po idkach, to musze uzyc albo HQL albo natywnego
	 *  nie fajnie. nie podoba mi sie
	 */
	@Override
	public List<TimeMooseStatus> getTimeMooseStatusesForMyWall(int userId) {
		
		List<TimeMooseStatus> statuses = getSession().createNativeQuery(
			"select distinct s.timeMooseStatusId, s.creationDate, s.lastUpdateDate, s.latitude, s.longitude, s.text, s.userId " +
			"from timemoosestatuses as s " +
			"join relationships on s.userId=relationships.friendId " +
			"join users on relationships.userId=users.userId " +
			"where users.userId = :userId or s.userId = :userId " +
			"order by s.creationDate DESC", TimeMooseStatus.class)
			.setParameter("userId", userId)
			.getResultList();
		
		return statuses;
	}
}
