package com.fronds.util;

import com.fronds.database.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Qbek on 2016-12-13.
 */
@Component
public interface UserDao {
    public User saveUser(User user);
    public User getUserByLogin(String login);
    public List<User> getUserList();
}
