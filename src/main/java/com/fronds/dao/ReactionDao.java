package com.fronds.dao;

import java.util.List;

import com.fronds.domain.model.Reaction;
import com.fronds.domain.model.ReactionType;

public interface ReactionDao {
	public void saveReaction(Reaction reaction);
	public void deleteReaction(int reactionId);
	public List<Reaction> getReactionsForStatus(int statusId);
	public ReactionType getMyReaction(int statusId, int userId);
}
