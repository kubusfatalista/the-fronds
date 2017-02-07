package com.fronds.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fronds.dao.UserDao;
import com.fronds.domain.model.User;
import com.fronds.domain.model.User_;

/**
 * Created by Qbek on 2016-12-13.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	
	public void saveUser(User user) {	
		save(user);
	}

	public User getUserByLogin(String login) {
		
		CriteriaQuery<User> criteria = getCriteriaBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.where(getCriteriaBuilder().equal(root.get(User_.login), login));
		
		return getSession()
				.createQuery(criteria)
				.getSingleResult();
	}

	public List<User> getUserList() {

		CriteriaQuery<User> criteria = getCriteriaBuilder().createQuery(User.class);
		criteria.from(User.class);

		return getSession().createQuery(criteria).getResultList();
	}
	
	public User getUserById(int id) {
		return getByKey(id);
	}
}
