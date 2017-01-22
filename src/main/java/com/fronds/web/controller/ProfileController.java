package com.fronds.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fronds.database.util.FtpUtil;
import com.fronds.model.User;
import com.fronds.service.UserPhotoService;
import com.fronds.service.UserService;

/**
 * Created by Qbek on 2016-12-15.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UserService userService;
    
    @Autowired
    UserPhotoService userPhotoService;
    
    @Autowired
    ServletContext context;

    @RequestMapping(method = GET)
    @Secured({"ROLE_REGULAR", "ROLE_ADMIN"})
    public String profile(Model model) {
        if(!model.containsAttribute("user")) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName(); //get logged in username
            model.addAttribute(userService.getUserByLogin(name));
        }
        return "profile";
    }

    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    @ResponseBody
    public void displayDocument(HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        byte[] img = FtpUtil.getImage(context.getRealPath("/"), userService.getUserByLogin(name).getProfilePicture());
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        try {
            response.getOutputStream().write(img);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = "/addPhoto", method = RequestMethod.POST)
    public String addPhotoToAlbum(
            @RequestPart("addedPhoto") Part profilePicture,
            RedirectAttributes model) {
    	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        User user = userService.getUserByLogin(name);
        long creationDate = new Date().getTime();
    	String addedPictureName = user.getLogin() + creationDate;

        if(profilePicture != null) {
            try {
                profilePicture.write(context.getRealPath("/") + addedPictureName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FtpUtil.saveImage(context.getRealPath("/"), addedPictureName);
        userPhotoService.saveUserPhoto(name, addedPictureName);
        
        model.addFlashAttribute("user", user);
        return "redirect:/profile/albums";
    }
    
    @RequestMapping(value = "/albums", method = GET)
    public String albums(Model model) {
        if(!model.containsAttribute("user")) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName(); //get logged in username
            model.addAttribute(userService.getUserByLogin(name));
        }
        model.addAttribute(userPhotoService.getUserPhotosByAlbumId(1));
        return "userAlbums";
    }
    
    @RequestMapping(value = "/albums/imageDisplay/{imageName}", method = RequestMethod.GET)
    @ResponseBody
    public void showAlbumImage(@PathVariable String imageName, HttpServletResponse response) {
        byte[] img = FtpUtil.getImage(context.getRealPath("/"), imageName);
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
