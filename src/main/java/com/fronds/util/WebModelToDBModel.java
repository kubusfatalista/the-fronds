package com.fronds.util;

import com.fronds.database.model.User;
import com.fronds.database.model.UserRole;
import com.fronds.web.model.UserWEB;

import java.util.Date;

/**
 * Created by Qbek on 2016-12-15.
 */
public class WebModelToDBModel {

    public static User createUser(UserWEB userWEB) {
        User user = new User();
        user.setLastName(userWEB.getLastName());
        user.setFirstName(userWEB.getFirstName());
        user.setPassword(userWEB.getPassword());
        user.setLogin(userWEB.getLogin());
        user.setProfilePicture(userWEB.getProfilePicture());
        user.setRegistrationDate(new Date().getTime());
        user.setRole(UserRole.REGULAR);
        return user;
    }

}
