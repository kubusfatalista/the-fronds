package com.fronds.util;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.fronds.database.model.User;
import com.fronds.database.util.HibernateUtil;

/**
 * Created by Qbek on 2016-12-13.
 */
@Component
public class UserDaoImpl implements UserDao {
	@Override
	public User saveUser(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		return user;
	}

	@Override
	public User getUserByLogin(String login) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);
		criteria.where(builder.equal(root.get("login"), login));
		
		User user = session.createQuery(criteria).getSingleResult();
		session.getTransaction().commit();
		return user;
	}

	@Override
	public List<User> getUserList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);

		List<User> userList = session.createQuery(criteria).getResultList();

		return userList;
	}
}
