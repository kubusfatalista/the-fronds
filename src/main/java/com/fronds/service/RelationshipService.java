package com.fronds.service;

import java.util.List;
import java.util.Map;

import com.fronds.domain.model.Relationship;

public interface RelationshipService {
	
	public void saveRelationship(Relationship relationship);
	public void updateRelationship(Relationship relationship);
	public List<Relationship> getMyFriends(int userId);
	public Map<Integer, Relationship> getMyFriendsMap(int userId);
	public List<Relationship> getMyInvitations(int userId);
	public void sendFriendRequest(int userId, int friendId);

}
