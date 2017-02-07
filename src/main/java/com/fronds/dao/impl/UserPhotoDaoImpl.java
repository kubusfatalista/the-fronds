package com.fronds.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fronds.dao.UserPhotoDao;
import com.fronds.domain.model.PhotoAlbum;
import com.fronds.domain.model.PhotoAlbum_;
import com.fronds.domain.model.User;
import com.fronds.domain.model.UserPhoto;
import com.fronds.domain.model.UserPhoto_;
import com.fronds.domain.model.User_;

@Repository("userPhotoDao")
public class UserPhotoDaoImpl extends AbstractDao<Integer, UserPhoto> implements UserPhotoDao {

	@Override
	public void saveUserPhoto(UserPhoto userPhoto) {
		save(userPhoto);
	}

	@Override
	public List<UserPhoto> getUserPhotosByAlbumId(long albumId) {
		CriteriaQuery<UserPhoto> criteria = getCriteriaBuilder().createQuery(UserPhoto.class);
		Root<UserPhoto> root = criteria.from(UserPhoto.class);
		criteria.select(root);
		criteria.orderBy(getCriteriaBuilder().desc(root.get(UserPhoto_.creationDate)));
		criteria.where(getCriteriaBuilder().equal(root.get(UserPhoto_.photoAlbum), albumId));

		return getSession().createQuery(criteria).getResultList();
	}
	
	@Override
	public List<UserPhoto> getAllUserPhotos(int userId) {
		CriteriaQuery<UserPhoto> criteria = getCriteriaBuilder().createQuery(UserPhoto.class);
		Root<UserPhoto> root = criteria.from(UserPhoto.class);
		Join<UserPhoto, PhotoAlbum> album = root.join(UserPhoto_.photoAlbum);
		Join<PhotoAlbum, User> user = album.join(PhotoAlbum_.user);
		
		criteria.where(getCriteriaBuilder().equal(user.get(User_.userId), userId));

		return getSession().createQuery(criteria).getResultList();
	}
}
