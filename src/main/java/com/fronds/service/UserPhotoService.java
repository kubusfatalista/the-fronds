package com.fronds.service;

import java.util.List;

import com.fronds.model.UserPhoto;

public interface UserPhotoService {
	public void saveUserPhoto(UserPhoto userPhoto);
	public void saveUserPhoto(String userLogin, String savedPhotoName);
	public List<UserPhoto> getUserPhotosByAlbumId(long id);
}
