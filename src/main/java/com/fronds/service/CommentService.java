package com.fronds.service;

import java.util.List;

import com.fronds.domain.model.Comment;

public interface CommentService {
	
	public void saveComment(Comment comment);
	public Comment getCommentById(int commentId);
	public List<Comment> getCommentsForStatus(int statusId);
	public void createComment(String text, int statusId, int userId);

}
