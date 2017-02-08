package com.fronds.service;

import java.util.List;

import com.fronds.domain.model.User;
import com.fronds.dto.FrondDto;

public interface UserService {

	void saveUser(User user);
	
	void createNewUser(User user);

	User getUserByLogin(String userLogin);
	
	User getUserById(int userId);

	List<User> getUserList();
	
	List<FrondDto> getAllUsersWithFrondsStatusesList(int userId);

}