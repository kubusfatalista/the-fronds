package com.fronds.dao;

import java.util.List;

import com.fronds.domain.model.Comment;

public interface CommentDao {
	public void saveComment(Comment comment);
	public Comment getCommentById(int commentId);
	public List<Comment> getCommentsForStatus(int statusId);

}
