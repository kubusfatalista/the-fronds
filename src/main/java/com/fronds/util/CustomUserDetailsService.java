package com.fronds.util;

import com.fronds.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qbek on 2016-12-13.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) {
        User user = userDao.getUserByLogin(s);
        if(user != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().authority()));
            org.springframework.security.core.userdetails.User details = new org.springframework.security.core.userdetails.User(
                    user.getLogin(), user.getPassword(), authorities);            
            return details;
            }
        throw new UsernameNotFoundException("No nie znaleziono takiego uzytkownika");
        }
}