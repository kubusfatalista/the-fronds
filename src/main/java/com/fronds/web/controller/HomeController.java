package com.fronds.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fronds.domain.model.User;
import com.fronds.service.UserService;
import com.fronds.util.CustomUser;
import com.fronds.util.FileRepository;

/**
 * Created by Qbek on 2016-12-11.
 */
@Controller
@RequestMapping({"/", "/homepage", "/login"})
public class HomeController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
    @Autowired
    ServletContext context;

    @Autowired
    UserService userService;
    
    @Autowired
    @Qualifier("localFileRepository")
    FileRepository fileRepository;
	
    @RequestMapping(method=RequestMethod.GET)
    public String home() {
        return "welcome";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(
            @RequestPart("profilePicture") Part profilePicture,
            User user, Errors errors,
            RedirectAttributes model) {
        if(errors.hasErrors())
            return "registerForm";
        userService.createNewUser(user);
        if(profilePicture != null) {
            try {
                profilePicture.write(context.getRealPath("/") + user.getProfilePicture());
            } catch (IOException e) {
            	logger.error(e);
            }
        }
        fileRepository.saveProfileImage(context.getRealPath("/"),user.getProfilePicture());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().authority()));
        CustomUser customUser = new CustomUser(user.getLogin(), user.getPassword(), authorities);
		customUser.setId(user.getUserId());
        Authentication authentication =  new UsernamePasswordAuthenticationToken(customUser, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addFlashAttribute("user", user);
        return "redirect:/myProfile";
    }
}