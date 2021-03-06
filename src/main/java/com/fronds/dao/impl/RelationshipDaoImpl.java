package com.fronds.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fronds.dao.RelationshipDao;
import com.fronds.domain.model.Relationship;
import com.fronds.domain.model.RelationshipStatus;
import com.fronds.domain.model.Relationship_;

@Repository("relationshipDao")
public class RelationshipDaoImpl extends AbstractDao<Integer, Relationship> implements RelationshipDao {
	
	@Override
	public Relationship getRelationshipById(int relationshipId) {
		return getByKey(relationshipId);
	}

	@Override
	public void saveRelationship(Relationship relationship) {
		save(relationship);
	}

	@Override
	public void updateRelationship(Relationship relationship) {
		update(relationship);
	}
	
	@Override  // TODO JEZ� popraw to bo to jest rak z przerzutami co tu sie za moment wydarzy
	public Relationship getRelationshipByUserAndFriendIds(int userId, int friendId) {
		
		CriteriaQuery<Relationship> criteria = getCriteriaBuilder().createQuery(Relationship.class);
		Root<Relationship> root = criteria.from(Relationship.class);
		criteria.where(getCriteriaBuilder().and(
				getCriteriaBuilder().equal(root.get(Relationship_.user), userId),
				getCriteriaBuilder().equal(root.get(Relationship_.friend), friendId)));
		
		return getSession().createQuery(criteria).getSingleResult();
	}

	@Override
	public List<Relationship> getMyRelationships(int userId) {
		CriteriaQuery<Relationship> criteria = getCriteriaBuilder().createQuery(Relationship.class);
		Root<Relationship> root = criteria.from(Relationship.class);
		criteria.where(getCriteriaBuilder().equal(root.get(Relationship_.user), userId));
		return getSession().createQuery(criteria).getResultList();
	}

	@Override
	public List<Relationship> getMyInvitations(int userId) {
		CriteriaQuery<Relationship> criteria = getCriteriaBuilder().createQuery(Relationship.class);
		Root<Relationship> root = criteria.from(Relationship.class);
		criteria.where(getCriteriaBuilder().and(
				getCriteriaBuilder().equal(root.get(Relationship_.user), userId),
				getCriteriaBuilder().equal(root.get(Relationship_.relationshipStatus), RelationshipStatus.INVITATION_WAITING)
				));
		
		return getSession().createQuery(criteria).getResultList();
	}
	
	@Override
	public List<Relationship> getMyFronds(int userId) {
		CriteriaQuery<Relationship> criteria = getCriteriaBuilder().createQuery(Relationship.class);
		Root<Relationship> root = criteria.from(Relationship.class);
		criteria.where(getCriteriaBuilder().and(
					getCriteriaBuilder().equal(root.get(Relationship_.user), userId)),
					getCriteriaBuilder().equal(root.get(Relationship_.relationshipStatus), RelationshipStatus.FRONDS)
				);
		return getSession().createQuery(criteria).getResultList();
	}
	
	@Override
	public Long getMyInvitationsCount(int userId) {
		CriteriaQuery<Long> criteria = getCriteriaBuilder().createQuery(Long.class);
		Root<Relationship> root = criteria.from(Relationship.class);
		criteria.multiselect(getCriteriaBuilder().count(root));
		criteria.where(getCriteriaBuilder().and(
				getCriteriaBuilder().equal(root.get(Relationship_.user), userId),
				getCriteriaBuilder().equal(root.get(Relationship_.relationshipStatus), RelationshipStatus.INVITATION_WAITING)));
		
		return getSession().createQuery(criteria).getSingleResult();
	}
}
