package com.fronds.service;

import java.util.List;

import com.fronds.domain.model.Reaction;
import com.fronds.domain.model.ReactionType;

public interface ReactionService {
	public void saveReaction(Reaction reaction);
	public void deleteReaction(int reactionId);
	public List<Reaction> getReactionsForStatus(int statusId);
	public void createReaction(ReactionType reactionType, int statusId, int userId);
	public ReactionType getMyReaction(int statusId, int userId);
	public String getMyReactionAsString(int statusId, int userId);
}
