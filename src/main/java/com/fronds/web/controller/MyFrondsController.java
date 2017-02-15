package com.fronds.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fronds.domain.model.User;
import com.fronds.service.RelationshipService;
import com.fronds.service.UserService;
import com.fronds.util.Attributes;
import com.fronds.util.FileRepository;

@Controller
@Secured({ "ROLE_REGULAR", "ROLE_ADMIN" })
@RequestMapping("/myFronds")
public class MyFrondsController {
	
	@Autowired
	RelationshipService relationshipService;
	
	@Autowired
	UserService userService;

	@Autowired
	@Qualifier("localFileRepository")
	FileRepository fileRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String myFronds(Model model, HttpSession session) {
		User user = userService.getUserById((int) session.getAttribute(Attributes.USER_ID));
		if (!model.containsAttribute("user")) {
			model.addAttribute(user);
		}
		model.addAttribute(relationshipService.getMyFronds((int) session.getAttribute(Attributes.USER_ID)));
		return "myFronds";
	}

}
