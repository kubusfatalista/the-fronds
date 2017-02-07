package com.fronds.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fronds.service.RelationshipService;
import com.fronds.service.TimeMooseStatusService;
import com.fronds.service.UserPhotoService;
import com.fronds.service.UserService;
import com.fronds.util.Attributes;
import com.fronds.util.FileRepository;

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
	UserPhotoService userPhotoService;

	@Autowired
	ServletContext context;
	
	@Autowired
	RelationshipService relationshipService;
	
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
		byte[] img = fileRepository
				.getImage(userService.getUserById((int) session.getAttribute(Attributes.USER_ID)).getProfilePicture()+"m");
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		try {
			response.getOutputStream().write(img);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	@RequestMapping(value = "/miniatureDisplay", method = RequestMethod.GET)
	@ResponseBody
	public void displayMiniature(HttpServletResponse response, HttpSession session) {
		byte[] img = fileRepository
				.getImage(userService.getUserById((int) session.getAttribute(Attributes.USER_ID)).getProfilePicture()+"xs");
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		try {
			response.getOutputStream().write(img);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	@RequestMapping(value = "/sendFriendInvitation", method = RequestMethod.GET)
	public String sendFriendInvitation(@RequestParam("friendId") int friendId, HttpSession session) {
		relationshipService.sendFriendRequest((int) session.getAttribute(Attributes.USER_ID), friendId);
		return "redirect:/myProfile";
	}
	
	@RequestMapping(value = "/createTimeMooseStatus", method = RequestMethod.GET)
	public String createTimeMooseStatus(@RequestParam("statusText") String statusText, HttpSession session) {
		timeMooseStatusService.createTimeMooseStatus((int) session.getAttribute(Attributes.USER_ID), statusText);
		return "redirect:/myProfile";
	}

	@RequestMapping(value = "/addPhoto", method = RequestMethod.POST)
	public String addPhotoToAlbum(@RequestPart("addedPhoto") Part profilePicture, RedirectAttributes model, HttpSession session) {

		long creationDate = new Date().getTime();
		String addedPictureName = (String)session.getAttribute("userName") + creationDate;

		if (profilePicture != null) {
			try {
				profilePicture.write(context.getRealPath("/") + addedPictureName);
			} catch (IOException e) {
				logger.error(e);
			}
		}
		fileRepository.saveImage(context.getRealPath("/"), addedPictureName);
		userPhotoService.saveUserPhoto((int) session.getAttribute(Attributes.USER_ID), addedPictureName);

		return "redirect:/myAlbums";
	}

}
