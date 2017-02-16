package com.fronds.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	
	
	public Relationship getRelationshipByUserAndFriendIds(int userId, int friendId) {
		return relationshipDao.getRelationshipByUserAndFriendIds(userId, friendId);
	}
	
	@Override
	public Relationship getRelationshipById(int relationshipId) {
		return relationshipDao.getRelationshipById(relationshipId);
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
	public void sendFriendRequest(int userId, int friendId) {
		Relationship relationshipSender = new Relationship();
		User sender = new User();
		sender.setUserId(userId);
		User receiver = new User();
		receiver.setUserId(friendId);
		relationshipSender.setUser(sender);
		relationshipSender.setFriend(receiver);
		relationshipSender.setRelationshipStatus(RelationshipStatus.INVITATION_SENT);
		Relationship relationshipReceiver = new Relationship();
		relationshipReceiver.setUser(receiver);
		relationshipReceiver.setFriend(sender);
		relationshipReceiver.setRelationshipStatus(RelationshipStatus.INVITATION_WAITING);
		relationshipDao.saveRelationship(relationshipReceiver);
		relationshipDao.saveRelationship(relationshipSender);
	}
	
	@Override
	public Map<Integer, Relationship> getMyRelationshipsMap(int userId) {
		List<Relationship> list = relationshipDao.getMyRelationships(userId);
		Map<Integer, Relationship> map = list.stream().collect(Collectors.toMap(n -> ((Relationship)n).getFriend().getUserId() , c -> c));
		return map;
	}

	@Override
	public List<Relationship> getMyInvitations(int userId) {
		return relationshipDao.getMyInvitations(userId);
	}
	
	@Override
	public List<Relationship> getMyFronds(int userId) {
		return relationshipDao.getMyFronds(userId);
	}
	
	// TODO PRZEPRASZAM
	// TODO MODEL BAZY LVL OVER 9000
	
	@Override
	public void acceptInvitation(int relationshipId) {
		Relationship relationship = relationshipDao.getRelationshipById(relationshipId);
		relationship.setRelationshipStatus(RelationshipStatus.FRONDS);
		Relationship relationship2 = relationshipDao.
				getRelationshipByUserAndFriendIds(relationship.getFriend().getUserId(), relationship.getUser().getUserId());
		relationship2.setRelationshipStatus(RelationshipStatus.FRONDS);
		
	}
	
	@Override
	public void declineInvitation(int relationshipId) {
		Relationship relationship = relationshipDao.getRelationshipById(relationshipId);
		relationship.setRelationshipStatus(RelationshipStatus.BLOCKED);
		Relationship relationship2 = relationshipDao.
				getRelationshipByUserAndFriendIds(relationship.getFriend().getUserId(), relationship.getUser().getUserId());
		relationship2.setRelationshipStatus(RelationshipStatus.BLOCKED);
	}
	
	@Override
	public Long getMyInvitationsCount(int userId) {
		return relationshipDao.getMyInvitationsCount(userId);
	}
}
