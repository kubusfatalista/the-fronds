package com.fronds.service.impl;

import java.util.List;

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
		user.setId(userId);
		User friend = new User();
		friend.setId(friendId);
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
	public List<Relationship> getMyInvitations(int userId) {
		return relationshipDao.getMyInvitations(userId);
	}

}
