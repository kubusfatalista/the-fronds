package com.fronds.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fronds.service.TimeMooseStatusService;
import com.fronds.util.FileRepository;

@Controller
@Secured({ "ROLE_REGULAR", "ROLE_ADMIN" })
@RequestMapping("/profile")
public class ProfileController { // /profile daje wszystkie profile, /profile/{id} konkretny

	@Autowired
	TimeMooseStatusService timeMooseStatusService;

	@Autowired
	@Qualifier("localFileRepository")
	FileRepository fileRepository;

	@RequestMapping(value = "{userId}", method = RequestMethod.GET)
	public String profile(@PathVariable int userId, Model model, HttpSession session) {
		model.addAttribute(timeMooseStatusService.getTimeMooseStatusesForUserId(userId));
		return "profile";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String profileDefault() {
		return "redirect:/myProfile";
	}
	
	
}
