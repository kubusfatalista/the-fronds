package com.fronds.web.controller;

import com.fronds.database.util.FtpUtil;
import com.fronds.util.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Qbek on 2016-12-15.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UserDao userDao;
    @Autowired
    ServletContext context;

    @RequestMapping(method = GET)
    @Secured({"ROLE_REGULAR", "ROLE_ADMIN"})
    public String profile(Model model) {
        if(!model.containsAttribute("user")) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName(); //get logged in username
            model.addAttribute(userDao.getUserByLogin(name));
        }
        return "profile";
    }

//    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
//    public void showImage(HttpServletResponse response, HttpServletRequest request)
//            throws ServletException, IOException {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName(); //get logged in username
//        byte[] img = FtpUtil.getImage(context.getRealPath("/"), userDao.getUserByLogin(name).getProfilePicture());
//        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
//        response.getOutputStream().write(img); // tutaj tablice bajtow
//        response.getOutputStream().close();
//    }

    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    @ResponseBody
    public void displayDocument(HttpServletResponse response) {
//        Path imagePath = Paths.get("C:/Koala.jpg");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        byte[] img = FtpUtil.getImage(context.getRealPath("/"), userDao.getUserByLogin(name).getProfilePicture());
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        try {
            response.getOutputStream().write(img);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
