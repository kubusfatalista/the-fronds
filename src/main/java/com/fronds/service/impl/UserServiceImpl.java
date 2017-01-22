package com.fronds.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronds.dao.PhotoAlbumDao;
import com.fronds.dao.UserDao;
import com.fronds.dao.UserPhotoDao;
import com.fronds.model.PhotoAlbum;
import com.fronds.model.User;
import com.fronds.model.UserPhoto;
import com.fronds.model.UserRole;
import com.fronds.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PhotoAlbumDao photoAlbumDao;
	
	@Autowired
	private UserPhotoDao userPhotoDao;

    /* (non-Javadoc)
	 * @see com.fronds.service.impl.UserService#saveUser(com.fronds.model.User)
	 */
    @Override
	public User saveUser(User user) {
    	user.setProfilePicture(user.getLogin() + new Date().getTime());
    	user.setRole(UserRole.REGULAR);
    	user = userDao.saveUser(user);
    	
    	PhotoAlbum profilesPhotoAlbum = new PhotoAlbum();
    	profilesPhotoAlbum.setAlbumName("Zdjêcia profilowe");
    	profilesPhotoAlbum.setAlbumDescription("");
    	profilesPhotoAlbum.setUser(user);
    	profilesPhotoAlbum.setAlbumCreationDate(new Date().getTime());
    	photoAlbumDao.savePhotoAlbum(profilesPhotoAlbum);
    	
    	UserPhoto userPhoto = new UserPhoto();
    	userPhoto.setCreationDate(new Date().getTime());
    	userPhoto.setImageDescription("Japa dodana przy rejestracji");
    	userPhoto.setImageTitle("Zdjêcie profilowe");
    	userPhoto.setImageSavedName(user.getProfilePicture());
    	userPhoto.setPhotoAlbum(profilesPhotoAlbum);
    	userPhotoDao.saveUserPhoto(userPhoto);
    	
    	Set<UserPhoto> userPhotos = new HashSet<>();
    	userPhotos.add(userPhoto);
    	profilesPhotoAlbum.setUserPhotos(userPhotos);
    	
    	Set<PhotoAlbum> photoAlbums = new HashSet<>();
    	photoAlbums.add(profilesPhotoAlbum);
    	user.setPhotoAlbums(photoAlbums);
    	return user;
    }
    /* (non-Javadoc)
	 * @see com.fronds.service.impl.UserService#getUserByLogin(java.lang.String)
	 */
    @Override
	public User getUserByLogin(String login) {
    	return userDao.getUserByLogin(login);
    }
    /* (non-Javadoc)
	 * @see com.fronds.service.impl.UserService#getUserList()
	 */
    @Override
	public List<User> getUserList() {
    	return userDao.getUserList();
    }
}
