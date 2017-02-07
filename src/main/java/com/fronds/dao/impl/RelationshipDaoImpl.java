package com.fronds.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fronds.dao.RelationshipDao;
import com.fronds.domain.model.Relationship;

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
		return new ArrayList<>();
	}

	@Override
	public List<Relationship> getMyInvitations(int userId) {
		return new ArrayList<>();
	}

}
