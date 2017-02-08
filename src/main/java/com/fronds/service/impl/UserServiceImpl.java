package com.fronds.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronds.dao.UserDao;
import com.fronds.domain.model.PhotoAlbum;
import com.fronds.domain.model.Relationship;
import com.fronds.domain.model.RelationshipStatus;
import com.fronds.domain.model.User;
import com.fronds.domain.model.UserPhoto;
import com.fronds.domain.model.UserRole;
import com.fronds.dto.FrondDto;
import com.fronds.service.PhotoAlbumService;
import com.fronds.service.RelationshipService;
import com.fronds.service.UserPhotoService;
import com.fronds.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PhotoAlbumService photoAlbumService;
	
	@Autowired
	private UserPhotoService userPhotoService;
	
	@Autowired
	private RelationshipService relationshipService;
	
	@Override
	public void createNewUser(User user) {
		saveUser(user);
		PhotoAlbum photoAlbum = photoAlbumService.createProfilePhotoAlbum(user);
		UserPhoto userPhoto = userPhotoService.saveUserProfilePhoto(photoAlbum, user.getProfilePicture());
		
    	Set<UserPhoto> userPhotos = new HashSet<>();
    	userPhotos.add(userPhoto);
    	photoAlbum.setUserPhotos(userPhotos);
    	
    	Set<PhotoAlbum> photoAlbums = new HashSet<>();
    	photoAlbums.add(photoAlbum);
    	user.setPhotoAlbums(photoAlbums);
		
	}

    /* (non-Javadoc)
	 * @see com.fronds.service.impl.UserService#saveUser(com.fronds.domain.model.User)
	 */
    @Override
	public void saveUser(User user) {
    	user.setProfilePicture(user.getLogin() + new Date().getTime());
    	user.setRole(UserRole.REGULAR);
    	userDao.saveUser(user);
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
	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}
	
	@Override
	public List<FrondDto> getAllUsersWithFrondsStatusesList(int userId) {
		List<User> users = userDao.getUserList();
		Map<Integer, Relationship> myFronds = relationshipService.getMyFriendsMap(userId);
		List<FrondDto> frondDtoList = new ArrayList<>();
		for(User user : users) {
			if(myFronds.containsKey(user.getUserId())) {
				frondDtoList.add(new FrondDto(
						user, resolveFrondStatus(
								userId, myFronds.get(
										user.getUserId()))));
			} else {
				if(user.getUserId() == userId)
					frondDtoList.add(new FrondDto(user, "ToTy"));
				else
					frondDtoList.add(new FrondDto(user, "Nieznajomy"));
			}
		}
		
		return frondDtoList;
	}
	
	private String resolveFrondStatus(int userId, Relationship relationship) {
		if(relationship.getRelationshipStatus() == RelationshipStatus.FRONDS) 
			return "Przyjaciul";
		if(relationship.getRelationshipStatus() == RelationshipStatus.INVITATION_SENT) {
			if(relationship.getUser().getUserId() == userId)
				return "Wyslano";
			else
				return "Oczekuje";
		}
		return "Zablokowany";
	}
}
