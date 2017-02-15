package com.fronds.dao;

import java.util.List;

import com.fronds.domain.model.Relationship;

public interface RelationshipDao {
	
	public Relationship getRelationshipById(int relationshipId);
	public void saveRelationship(Relationship relationship);
	public void updateRelationship(Relationship relationship);
	public List<Relationship> getMyRelationships(int userId);
	public List<Relationship> getMyInvitations(int userId);
	public List<Relationship> getMyFronds(int userId);
	public Long getMyInvitationsCount(int userId);
	
}
