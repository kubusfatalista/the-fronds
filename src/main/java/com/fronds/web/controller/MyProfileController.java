package com.fronds.web.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fronds.service.TimeMooseStatusService;
import com.fronds.service.UserService;
import com.fronds.util.Attributes;
import com.fronds.util.FileRepository;
import com.fronds.util.ImagesUtil;

/**
 * Created by Qbek on 2016-12-15.
 */
@Controller
@Secured({ "ROLE_REGULAR", "ROLE_ADMIN" })
@RequestMapping("/myProfile")
public class MyProfileController {
	
	private static final Logger logger = Logger.getLogger(MyProfileController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	TimeMooseStatusService timeMooseStatusService;

	@Autowired
	@Qualifier("localFileRepository")
	FileRepository fileRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String myProfile(Model model, HttpSession session) {
		if (!model.containsAttribute("user")) {
			model.addAttribute(userService.getUserById((int) session.getAttribute(Attributes.USER_ID)));
		}
		model.addAttribute(timeMooseStatusService.getTimeMooseStatusesForUserId((int) session.getAttribute(Attributes.USER_ID)));
		return "myProfile";
	}

	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	@ResponseBody
	public void displayDocument(HttpServletResponse response, HttpSession session) {
		byte[] imgData = fileRepository
				.getImage(userService.getUserById((int) session.getAttribute(Attributes.USER_ID)).getProfilePicture()+"m");
		ImagesUtil.writeImageToResponse(imgData, response, logger);
	}
	
	@RequestMapping(value = "/miniatureDisplay", method = RequestMethod.GET)
	@ResponseBody
	public void displayMiniature(HttpServletResponse response, HttpSession session) {
		byte[] imgData = fileRepository
				.getImage(userService.getUserById((int) session.getAttribute(Attributes.USER_ID)).getProfilePicture()+"xs");
		ImagesUtil.writeImageToResponse(imgData, response, logger);
	}
	
	@RequestMapping(value = "/createTimeMooseStatus", method = RequestMethod.GET)
	public String createTimeMooseStatus(@RequestParam("statusText") String statusText, HttpSession session) {
		timeMooseStatusService.createTimeMooseStatus((int) session.getAttribute(Attributes.USER_ID), statusText);
		return "redirect:/myProfile";
	}
}
