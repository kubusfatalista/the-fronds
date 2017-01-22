package com.fronds.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fronds.dao.AbstractDao;
import com.fronds.dao.UserPhotoDao;
import com.fronds.model.UserPhoto;

@Repository("userPhotoDao")
public class UserPhotoDaoImpl extends AbstractDao<Integer, UserPhoto> implements UserPhotoDao {

	@Override
	public void saveUserPhoto(UserPhoto userPhoto) {
		save(userPhoto);
	}

	@Override
	public List<UserPhoto> getUserPhotosByAlbumId(long id) {
		CriteriaQuery<UserPhoto> criteria = getCriteriaBuilder().createQuery(UserPhoto.class);
		Root<UserPhoto> root = criteria.from(UserPhoto.class);
		criteria.select(root);
		criteria.orderBy(getCriteriaBuilder().desc(root.get("creationDate")));
		criteria.where(getCriteriaBuilder().equal(root.get("photoAlbum"), id));

		return getSession().createQuery(criteria).getResultList();
	}
}