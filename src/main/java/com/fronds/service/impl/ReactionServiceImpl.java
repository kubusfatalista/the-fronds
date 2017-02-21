package com.fronds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronds.dao.ReactionDao;
import com.fronds.domain.model.Reaction;
import com.fronds.domain.model.ReactionType;
import com.fronds.service.ReactionService;
import com.fronds.service.TimeMooseStatusService;
import com.fronds.service.UserService;

@Service("reactionService")
@Transactional
public class ReactionServiceImpl implements ReactionService {
	
	@Autowired
	ReactionDao reactionDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TimeMooseStatusService timeMooseStatusService;

	@Override
	public void saveReaction(Reaction reaction) {
		reactionDao.saveReaction(reaction);
	}

	@Override
	public void deleteReaction(int reactionId) {
		reactionDao.deleteReaction(reactionId);
	}

	@Override
	public List<Reaction> getReactionsForStatus(int statusId) {
		return reactionDao.getReactionsForStatus(statusId);
	}

	@Override
	public void createReaction(ReactionType reactionType, int statusId, int userId) {
		Reaction reaction = new Reaction();
		reaction.setReactionType(reactionType);
		reaction.setTimeMooseStatus(timeMooseStatusService.getTimeMooseStatusById(statusId));
		reaction.setUser(userService.getUserById(userId));
		saveReaction(reaction);
	}

	@Override
	public ReactionType getMyReaction(int statusId, int userId) {
//		return reactionDao.getMyReaction(statusId, userId);
		return null;
	}
	
	@Override
	public String getMyReactionAsString(int statusId, int userId) {
		ReactionType reactionType = reactionDao.getMyReaction(statusId, userId);
		if(reactionType == null)
			return null;
		switch(reactionType) {
		case FUNNY:
			return null;
		case HATE:
			return "Hejt";
		case LIKE:
			return "Lajk";
		case SAD4YOU:
			return null;
		default:
			return null;
		}
	}
}
