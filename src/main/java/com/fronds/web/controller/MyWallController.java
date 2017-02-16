package com.fronds.web.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fronds.service.TimeMooseStatusService;
import com.fronds.service.UserService;
import com.fronds.util.Attributes;
import com.fronds.util.FileRepository;
import com.fronds.util.ImagesUtil;

@Controller
@Secured({ "ROLE_REGULAR", "ROLE_ADMIN" })
@RequestMapping("/myWall")
public class MyWallController {
	
	private static final Logger logger = Logger.getLogger(MyWallController.class);
	
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
		model.addAttribute(timeMooseStatusService.getTimeMooseStatusesForMyWall((int) session.getAttribute(Attributes.USER_ID)));
		return "myWall";
	}
	
	@RequestMapping(value = "/{profileId}/miniatureDisplay", method = RequestMethod.GET)
	@ResponseBody
	public void displayMiniature(@PathVariable int profileId, HttpServletResponse response, HttpSession session) {
		byte[] imgData = fileRepository
				.getImage(userService.getUserById(profileId).getProfilePicture()+"xs");
		ImagesUtil.writeImageToResponse(imgData, response, logger);
	}
	
}
