package com.fronds.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronds.dao.RelationshipDao;
import com.fronds.domain.model.Relationship;
import com.fronds.domain.model.RelationshipStatus;
import com.fronds.domain.model.User;
import com.fronds.service.RelationshipService;

@Service("relationshipService")
@Transactional
public class RelationshipServiceImpl implements RelationshipService {
	
	@Autowired
	RelationshipDao relationshipDao;
	
	public void sendFriendRequest(int userId, int friendId) {
		Relationship relationship = new Relationship();
		User user = new User();
		user.setUserId(userId);
		User friend = new User();
		friend.setUserId(friendId);
		relationship.setUser(user);
		relationship.setFriend(friend);
		relationship.setRelationshipStatus(RelationshipStatus.INVITATION_SENT);
		relationshipDao.saveRelationship(relationship);
	}

	@Override
	public void saveRelationship(Relationship relationship) {
		relationshipDao.saveRelationship(relationship);
	}

	@Override
	public void updateRelationship(Relationship relationship) {
		relationshipDao.updateRelationship(relationship);
	}

	@Override
	public List<Relationship> getMyFriends(int userId) {
		return relationshipDao.getMyFriends(userId);
	}
	
	@Override
	public Map<Integer, Relationship> getMyFriendsMap(int userId) {
		List<Relationship> list = relationshipDao.getMyFriends(userId);
		Map<Integer, Relationship> map = new HashMap<>();
		for(Relationship rel : list) {
			if(rel.getUser().getUserId() == userId) {
				map.put(rel.getFriend().getUserId(), rel);
			} else if(rel.getFriend().getUserId() == userId) {
				map.put(rel.getUser().getUserId(), rel);
			}
		}
		return map;
	}

	@Override
	public List<Relationship> getMyInvitations(int userId) {
		return relationshipDao.getMyInvitations(userId);
	}

}
