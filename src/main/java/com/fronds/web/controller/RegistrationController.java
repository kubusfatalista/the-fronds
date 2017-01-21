package com.fronds.web.controller;

import com.fronds.database.model.User;
import com.fronds.database.util.FtpUtil;
import com.fronds.util.UserDao;
import com.fronds.util.WebModelToDBModel;
import com.fronds.web.model.UserWEB;
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

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Qbek on 2016-12-11.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    ServletContext context;

    @Autowired
    private UserDao userDaoImpl;

    @RequestMapping(method = GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new UserWEB());
        return "registerForm";
    }

    @RequestMapping(method = POST)
    public String processRegistration(
            @RequestPart("profilePicture") Part profilePicture,
            @Valid UserWEB userWEB, Errors errors,
            RedirectAttributes model) {
        if(errors.hasErrors())
            return "registerForm";
        userWEB.setProfilePicture(userWEB.getLogin() + new Date().getTime());
        User user = userDaoImpl.saveUser(WebModelToDBModel.createUser(userWEB));
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
                userWEB.getLogin(), userWEB.getPassword(), authorities);
        Authentication authentication =  new UsernamePasswordAuthenticationToken(details, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addFlashAttribute("user", user);
        return "redirect:/profile";
    }
}
