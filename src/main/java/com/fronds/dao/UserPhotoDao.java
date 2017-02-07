package com.fronds.dao;

import java.util.List;

import com.fronds.domain.model.UserPhoto;

/**
 * @author Qbek
 *
 */
public interface UserPhotoDao {
	
	public void saveUserPhoto(UserPhoto userPhoto);
	public List<UserPhoto> getUserPhotosByAlbumId(long id);
	public List<UserPhoto> getAllUserPhotos(int id);

}
