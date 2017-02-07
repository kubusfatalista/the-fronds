package com.fronds.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fronds.dao.PhotoAlbumDao;
import com.fronds.domain.model.PhotoAlbum;
import com.fronds.domain.model.PhotoAlbum_;
import com.fronds.domain.model.User;

@Repository("photoAlbumDao")
public class PhotoAlbumDaoImpl extends AbstractDao<Integer, PhotoAlbum> implements PhotoAlbumDao {

	@Override
	public void savePhotoAlbum(PhotoAlbum photoAlbum) {
		save(photoAlbum);
	}

	@Override
	public List<PhotoAlbum> getAllUserAlbums(User user) {
		CriteriaQuery<PhotoAlbum> criteria = getCriteriaBuilder().createQuery(PhotoAlbum.class);
		Root<PhotoAlbum> root = criteria.from(PhotoAlbum.class);
		criteria.where(getCriteriaBuilder().equal(root.get(PhotoAlbum_.user), user));

		return getSession().createQuery(criteria).getResultList();
	}
	
}
