package com.fronds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronds.dao.UserPhotoDao;
import com.fronds.domain.model.PhotoAlbum;
import com.fronds.domain.model.UserPhoto;
import com.fronds.service.UserPhotoService;
import com.fronds.service.UserService;

@Service("userPhotoService")
@Transactional
public class UserPhotoServiceImpl implements UserPhotoService {
	
	@Autowired
	private UserPhotoDao userPhotoDao;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void saveUserPhoto(UserPhoto userPhoto) {
		userPhotoDao.saveUserPhoto(userPhoto);
	}
	
	@Override
	public void saveUserPhoto(PhotoAlbum album, String savedPhotoName) {
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setImageDescription("none");
        userPhoto.setImageSavedName(savedPhotoName);
        userPhoto.setImageTitle("test");
        userPhoto.setLongitude(-1);
        userPhoto.setLatitude(-1);
        userPhoto.setPhotoAlbum(album);
        saveUserPhoto(userPhoto);
	}
	
	@Override
	public UserPhoto saveUserProfilePhoto(PhotoAlbum album, String savedPhotoName) {
    	UserPhoto userPhoto = new UserPhoto();
    	userPhoto.setImageDescription("Japa dodana przy rejestracji");
    	userPhoto.setImageTitle("Zdjêcie profilowe");
    	userPhoto.setImageSavedName(savedPhotoName);
    	userPhoto.setPhotoAlbum(album);
        userPhoto.setLongitude(-1);
        userPhoto.setLatitude(-1);
    	userPhotoDao.saveUserPhoto(userPhoto);
    	return userPhoto;
	}
	
	@Override
	public void saveUserPhoto(int userId, String savedPhotoName) {
		PhotoAlbum currentAlbum = null;
        for(PhotoAlbum album : userService.getUserById(userId).getPhotoAlbums())
        	currentAlbum = album;

        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setImageDescription("none");
        userPhoto.setImageSavedName(savedPhotoName);
        userPhoto.setImageTitle("test");
        userPhoto.setLongitude(-1);
        userPhoto.setLatitude(-1);
        userPhoto.setPhotoAlbum(currentAlbum);
        saveUserPhoto(userPhoto);
	}

	@Override
	public List<UserPhoto> getUserPhotosByAlbumId(long id) {
		return userPhotoDao.getUserPhotosByAlbumId(id);
	}
	
	@Override
	public List<UserPhoto> getAllUserPhotos(int id) {
		return userPhotoDao.getAllUserPhotos(id);
	}
}
