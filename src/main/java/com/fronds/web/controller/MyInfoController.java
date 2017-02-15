package com.fronds.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fronds.service.UserService;
import com.fronds.util.Attributes;

@Controller
@Secured({ "ROLE_REGULAR", "ROLE_ADMIN" })
@RequestMapping("/myInfo")
public class MyInfoController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String myProfile(Model model, HttpSession session) {
		if (!model.containsAttribute("user")) {
			model.addAttribute(userService.getUserById((int) session.getAttribute(Attributes.USER_ID)));
		}
		return "myInfo";
	}

}
