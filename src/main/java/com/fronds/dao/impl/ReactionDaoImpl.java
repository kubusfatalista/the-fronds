package com.fronds.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fronds.dao.ReactionDao;
import com.fronds.domain.model.Reaction;
import com.fronds.domain.model.ReactionType;
import com.fronds.domain.model.Reaction_;

@Repository("reactionDao")
public class ReactionDaoImpl extends AbstractDao<Integer, Reaction> implements ReactionDao {
	
	@Override
	public void saveReaction(Reaction reaction) {
		save(reaction);
		
	}

	@Override
	public void deleteReaction(int reactionId) {
		delete(getByKey(reactionId));
	}
	
	@Override
	public List<Reaction> getReactionsForStatus(int statusId) {
		CriteriaQuery<Reaction> criteria = getCriteriaBuilder().createQuery(Reaction.class);
		Root<Reaction> root = criteria.from(Reaction.class);
		criteria.where(getCriteriaBuilder().equal(root.get(Reaction_.timeMooseStatus), statusId));
		criteria.orderBy(getCriteriaBuilder().desc(root.get(Reaction_.creationDate)));
		
		return getSession()
				.createQuery(criteria)
				.getResultList();
	}

	@Override
	public ReactionType getMyReaction(int statusId, int userId) throws NoResultException {
		CriteriaQuery<ReactionType> criteria = getCriteriaBuilder().createQuery(ReactionType.class);
		Root<Reaction> root = criteria.from(Reaction.class);
		criteria.select(root.get(Reaction_.reactionType));
		criteria.where(getCriteriaBuilder().and(getCriteriaBuilder().equal(root.get(Reaction_.timeMooseStatus), statusId),
				getCriteriaBuilder().equal(root.get(Reaction_.user), userId)));
		
		// jakie zabezpieczenie przed no result exception, kiedy tak na prawde go oczekuje czasem????
		List<ReactionType> list = getSession().createQuery(criteria).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}

}
