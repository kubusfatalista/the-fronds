package com.fronds.dao;

import java.util.List;

import com.fronds.domain.model.Relationship;

public interface RelationshipDao {
	
	public Relationship getRelationshipById(int relationshipId);
	public void saveRelationship(Relationship relationship);
	public void updateRelationship(Relationship relationship);
	public List<Relationship> getMyFriends(int userId);
	public List<Relationship> getMyInvitations(int userId);
	
}
