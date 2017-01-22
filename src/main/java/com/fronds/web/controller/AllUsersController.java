package com.fronds.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fronds.model.User;
import com.fronds.service.UserService;

/**
 * Created by Qbek on 2016-12-15.
 */
@Controller
@RequestMapping("/allUsers")
public class AllUsersController {

    @Autowired
    UserService userService;

    @RequestMapping(method= RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public List<User> users() {
        return userService.getUserList();
    }

}
