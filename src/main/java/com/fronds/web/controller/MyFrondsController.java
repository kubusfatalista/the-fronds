package com.fronds.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fronds.service.TimeMooseStatusService;
import com.fronds.util.Attributes;
import com.fronds.util.FileRepository;

@Controller
@Secured({ "ROLE_REGULAR", "ROLE_ADMIN" })
@RequestMapping("/myFronds")
public class MyFrondsController {
	
	@Autowired
	TimeMooseStatusService timeMooseStatusService;

	@Autowired
	@Qualifier("localFileRepository")
	FileRepository fileRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String myProfile(Model model, HttpSession session) {
		model.addAttribute(timeMooseStatusService.getTimeMooseStatusesForUserId((int) session.getAttribute(Attributes.USER_ID)));
		return "myFronds";
	}

}
