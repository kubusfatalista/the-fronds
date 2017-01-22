package com.fronds.dao;

import java.util.List;

import com.fronds.model.User;

/**
 * Created by Qbek on 2016-12-13.
 */

public interface UserDao {
    public User saveUser(User user);
    public User getUserByLogin(String login);
    public List<User> getUserList();
}
