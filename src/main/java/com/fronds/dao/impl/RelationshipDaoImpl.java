package com.fronds.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fronds.dao.RelationshipDao;
import com.fronds.domain.model.Relationship;
import com.fronds.domain.model.Relationship_;

@Repository("relationshipDao")
public class RelationshipDaoImpl extends AbstractDao<Integer, Relationship> implements RelationshipDao {

	@Override
	public void saveRelationship(Relationship relationship) {
		save(relationship);
	}

	@Override
	public void updateRelationship(Relationship relationship) {
		update(relationship);
	}

	@Override
	public List<Relationship> getMyFriends(int userId) {
		CriteriaQuery<Relationship> criteria = getCriteriaBuilder().createQuery(Relationship.class);
		Root<Relationship> root = criteria.from(Relationship.class);
		criteria.where(getCriteriaBuilder().or(
				getCriteriaBuilder().equal(root.get(Relationship_.user), userId),
				getCriteriaBuilder().equal(root.get(Relationship_.friend), userId)));
		
		return getSession().createQuery(criteria).getResultList();
	}

	@Override
	public List<Relationship> getMyInvitations(int userId) {
		return new ArrayList<>();
	}

}
