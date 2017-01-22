package com.fronds.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fronds.dao.AbstractDao;
import com.fronds.dao.UserDao;
import com.fronds.model.User;

/**
 * Created by Qbek on 2016-12-13.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	
	public User saveUser(User user) {	
		save(user);
		return user;
	}

	public User getUserByLogin(String login) {
		
		CriteriaQuery<User> criteria = getCriteriaBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);
		criteria.where(getCriteriaBuilder().equal(root.get("login"), login));
		
		User user = getSession()
				.createQuery(criteria)
				.setCacheable(true)
				.setCacheRegion("user")
				.getSingleResult();
		return user;
	}

	public List<User> getUserList() {

		CriteriaQuery<User> criteria = getCriteriaBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);

		List<User> userList = getSession().createQuery(criteria).getResultList();

		return userList;
	}
	
	public User getUserById(int id) {
		return getByKey(id);
	}
}
