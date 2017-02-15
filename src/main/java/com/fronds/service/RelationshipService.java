package com.fronds.service;

import java.util.List;
import java.util.Map;

import com.fronds.domain.model.Relationship;
import com.fronds.dto.FrondDto;

public interface RelationshipService {
	
	public void getRelationshipById(int relationshipId);
	public void saveRelationship(Relationship relationship);
	public void updateRelationship(Relationship relationship);
	public List<Relationship> getMyFriends(int userId);
	public Map<Integer, Relationship> getMyRelationshipsMap(int userId);
	public List<Relationship> getMyInvitations(int userId);
	public List<FrondDto> getMyFronds(int userId);
	public void sendFriendRequest(int userId, int friendId);
	public void acceptInvitation(int relationshipId);
	public void declineInvitation(int relationshipId);
	public Long getMyInvitationsCount(int userId);
}
