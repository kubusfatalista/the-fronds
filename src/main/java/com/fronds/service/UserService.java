package com.fronds.service;

import java.util.List;

import com.fronds.model.PhotoAlbum;
import com.fronds.model.User;

public interface UserService {

	User saveUser(User user);

	User getUserByLogin(String login);
	
	User getUserById(int id);

	List<User> getUserList();

}