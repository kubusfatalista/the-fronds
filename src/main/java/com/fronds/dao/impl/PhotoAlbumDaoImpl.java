package com.fronds.dao.impl;

import org.springframework.stereotype.Repository;

import com.fronds.dao.AbstractDao;
import com.fronds.dao.PhotoAlbumDao;
import com.fronds.model.PhotoAlbum;

@Repository("photoAlbumDao")
public class PhotoAlbumDaoImpl extends AbstractDao<Integer, PhotoAlbum> implements PhotoAlbumDao {

	@Override
	public void savePhotoAlbum(PhotoAlbum photoAlbum) {
		save(photoAlbum);
	}
	

}
