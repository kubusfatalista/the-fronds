package com.fronds.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fronds.dao.CommentDao;
import com.fronds.domain.model.Comment;
import com.fronds.domain.model.Comment_;

@Repository("commentDao")
public class CommentDaoImpl extends AbstractDao<Integer, Comment> implements CommentDao {

	@Override
	public void saveComment(Comment comment) {
		save(comment);
		
	}

	@Override
	public Comment getCommentById(int commentId) {
		return getByKey(commentId);
	}

	@Override
	public List<Comment> getCommentsForStatus(int statusId) {
		CriteriaQuery<Comment> criteria = getCriteriaBuilder().createQuery(Comment.class);
		Root<Comment> root = criteria.from(Comment.class);
		criteria.where(getCriteriaBuilder().equal(root.get(Comment_.timeMooseStatus), statusId));
		criteria.orderBy(getCriteriaBuilder().asc(root.get(Comment_.creationDate)));
		
		return getSession()
				.createQuery(criteria)
				.getResultList();
	}

	
	
	
}
