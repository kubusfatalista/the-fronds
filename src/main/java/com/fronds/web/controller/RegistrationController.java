package com.fronds.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fronds.database.util.FtpUtil;
import com.fronds.model.User;
import com.fronds.service.UserService;

/**
 * Created by Qbek on 2016-12-11.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    ServletContext context;

    @Autowired
    private UserService userService;

    @RequestMapping(method = GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new User());
        return "registerForm";
    }

    @RequestMapping(method = POST)
    public String processRegistration(
            @RequestPart("profilePicture") Part profilePicture,
            User user, Errors errors,
            RedirectAttributes model) {
        if(errors.hasErrors())
            return "registerForm";
        user = userService.saveUser(user);
        if(profilePicture != null) {
            try {
                profilePicture.write(context.getRealPath("/") + user.getProfilePicture());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FtpUtil.saveImage(context.getRealPath("/"),user.getProfilePicture());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_REGULAR"));
        org.springframework.security.core.userdetails.User details = new org.springframework.security.core.userdetails.User(
        		user.getLogin(), user.getPassword(), authorities);
        Authentication authentication =  new UsernamePasswordAuthenticationToken(details, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addFlashAttribute("user", user);
        return "redirect:/profile";
    }
}
