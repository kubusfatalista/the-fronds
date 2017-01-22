package com.fronds.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronds.dao.PhotoAlbumDao;
import com.fronds.dao.UserDao;
import com.fronds.dao.UserPhotoDao;
import com.fronds.model.PhotoAlbum;
import com.fronds.model.UserPhoto;
import com.fronds.service.UserPhotoService;

@Service("userPhotoService")
@Transactional
public class UserPhotoServiceImpl implements UserPhotoService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserPhotoDao userPhotoDao;
	
	@Autowired
	private PhotoAlbumDao photoAlbumDao;

	@Override
	public void saveUserPhoto(UserPhoto userPhoto) {
		userPhotoDao.saveUserPhoto(userPhoto);
	}
	
	@Override
	public void saveUserPhoto(String userLogin, String savedPhotoName) {
		PhotoAlbum currentAlbum = null;
        for(PhotoAlbum album : userDao.getUserByLogin(userLogin).getPhotoAlbums())
        	currentAlbum = album;

        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setCreationDate(new Date().getTime());
        userPhoto.setImageDescription("none");
        userPhoto.setImageSavedName(savedPhotoName);
        userPhoto.setImageTitle("test");
        userPhoto.setPhotoAlbum(currentAlbum);
        saveUserPhoto(userPhoto);
	}

	@Override
	public List<UserPhoto> getUserPhotosByAlbumId(long id) {
		return userPhotoDao.getUserPhotosByAlbumId(id);
	}
}
