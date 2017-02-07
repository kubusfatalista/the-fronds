package com.fronds.dao;

import java.util.List;

import com.fronds.domain.model.PhotoAlbum;
import com.fronds.domain.model.User;

public interface PhotoAlbumDao {
	public void savePhotoAlbum(PhotoAlbum photoAlbum);
	public List<PhotoAlbum> getAllUserAlbums(User user);
}
