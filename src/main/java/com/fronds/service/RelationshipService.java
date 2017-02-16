package com.fronds.service;

import java.util.List;
import java.util.Map;

import com.fronds.domain.model.Relationship;

public interface RelationshipService {
	
	public Relationship getRelationshipById(int relationshipId);
	public Relationship getRelationshipByUserAndFriendIds(int userId, int friendId);
	public void saveRelationship(Relationship relationship);
	public void updateRelationship(Relationship relationship);
	public Map<Integer, Relationship> getMyRelationshipsMap(int userId);
	public List<Relationship> getMyInvitations(int userId);
	public List<Relationship> getMyFronds(int userId);
	public void sendFriendRequest(int userId, int friendId);
	public void acceptInvitation(int relationshipId);
	public void declineInvitation(int relationshipId);
	public Long getMyInvitationsCount(int userId);
}
