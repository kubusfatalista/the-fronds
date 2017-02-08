package com.fronds.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.fronds.domain.model.User;
import com.fronds.service.UserService;

/**
 * Created by Qbek on 2016-12-13.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String s) {
		User user = userService.getUserByLogin(s);
		if (user != null) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getRole().authority()));
			CustomUser customUser = new CustomUser(user.getLogin(), user.getPassword(), authorities);
			customUser.setId(user.getUserId());
			return customUser;
		}
		throw new UsernameNotFoundException("No nie znaleziono takiego uzytkownika");
	}
}