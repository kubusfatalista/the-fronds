package com.fronds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronds.dao.PhotoAlbumDao;
import com.fronds.domain.model.PhotoAlbum;
import com.fronds.domain.model.User;
import com.fronds.service.PhotoAlbumService;

@Service("photoAlbumService")
@Transactional
public class PhotoAlbumServiceImpl implements PhotoAlbumService {
	
	@Autowired
	private PhotoAlbumDao photoAlbumDao;

	@Override
	public PhotoAlbum createProfilePhotoAlbum(User user) {
    	PhotoAlbum profilesPhotoAlbum = new PhotoAlbum();
    	profilesPhotoAlbum.setAlbumName("Zdjêcia profilowe");
    	profilesPhotoAlbum.setAlbumDescription("");
    	profilesPhotoAlbum.setUser(user);
    	photoAlbumDao.savePhotoAlbum(profilesPhotoAlbum);
    	return profilesPhotoAlbum;
	}
	
	@Override
	public void createNewAlbum(String albumName, String albumDescription, User user) {
    	PhotoAlbum profilesPhotoAlbum = new PhotoAlbum();
    	profilesPhotoAlbum.setAlbumName(albumName);
    	profilesPhotoAlbum.setAlbumDescription(albumDescription);
    	profilesPhotoAlbum.setUser(user);
    	photoAlbumDao.savePhotoAlbum(profilesPhotoAlbum);
	}

}
