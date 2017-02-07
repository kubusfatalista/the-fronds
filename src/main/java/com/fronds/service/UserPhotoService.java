package com.fronds.service;

import java.util.List;

import com.fronds.domain.model.PhotoAlbum;
import com.fronds.domain.model.UserPhoto;

public interface UserPhotoService {
	public void saveUserPhoto(UserPhoto userPhoto);
	public void saveUserPhoto(int userId, String savedPhotoName);
	public void saveUserPhoto(PhotoAlbum album, String savedPhotoName);
	public UserPhoto saveUserProfilePhoto(PhotoAlbum album, String savedPhotoName);
	public List<UserPhoto> getUserPhotosByAlbumId(long id);
	public List<UserPhoto> getAllUserPhotos(int id);
}
