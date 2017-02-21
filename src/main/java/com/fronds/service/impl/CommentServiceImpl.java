package com.fronds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronds.dao.CommentDao;
import com.fronds.domain.model.Comment;
import com.fronds.service.CommentService;
import com.fronds.service.TimeMooseStatusService;
import com.fronds.service.UserService;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TimeMooseStatusService timeMooseStatusService;

	@Override
	public void saveComment(Comment comment) {
		commentDao.saveComment(comment);
	}

	@Override
	public Comment getCommentById(int commentId) {
		return commentDao.getCommentById(commentId);
	}

	@Override
	public List<Comment> getCommentsForStatus(int statusId) {
		return commentDao.getCommentsForStatus(statusId);
	}

	@Override
	public void createComment(String text, int statusId, int userId) {
		Comment comment = new Comment();
		comment.setText(text);
		comment.setTimeMooseStatus(timeMooseStatusService.getTimeMooseStatusById(statusId));
		comment.setUser(userService.getUserById(userId));
		saveComment(comment);
	}

}
