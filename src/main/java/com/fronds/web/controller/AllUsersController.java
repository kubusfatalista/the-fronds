package com.fronds.web.controller;

import com.fronds.database.model.User;
import com.fronds.util.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Qbek on 2016-12-15.
 */
@Controller
@RequestMapping("/allUsers")
public class AllUsersController {

    @Autowired
    UserDao userDao;

    @RequestMapping(method= RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public List<User> users() {
        return userDao.getUserList();
    }

}
