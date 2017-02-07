package com.fronds.service;

import com.fronds.domain.model.PhotoAlbum;
import com.fronds.domain.model.User;

public interface PhotoAlbumService {
	public PhotoAlbum createProfilePhotoAlbum(User user);
	public void createNewAlbum(String albumName, String albumDescription, User user);
}
