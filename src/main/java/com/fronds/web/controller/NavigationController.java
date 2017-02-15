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

import com.fronds.service.RelationshipService;
import com.fronds.service.UserService;
import com.fronds.util.Attributes;
import com.fronds.util.FileRepository;
import com.fronds.util.ImagesUtil;

@Controller
@Secured({ "ROLE_REGULAR", "ROLE_ADMIN" })
@RequestMapping("/navi")
public class NavigationController {
	
	private static final Logger logger = Logger.getLogger(NavigationController.class);
	
	@Autowired
	@Qualifier("localFileRepository")
	FileRepository fileRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RelationshipService relationshipService;
	
	@RequestMapping(value = "/getInvitations", method = RequestMethod.GET)
	public String getInvitations(Model model, HttpSession session) {
		model.addAttribute(relationshipService.getMyInvitations((int) session.getAttribute(Attributes.USER_ID)));
		return "invitations :: invitationsList";
	}
	
	@RequestMapping(value = "/getInvitationsCount", method = RequestMethod.GET)
	@ResponseBody
	public String getInvitationsCount(HttpServletResponse response, HttpSession session) {
		return relationshipService.getMyInvitationsCount((int) session.getAttribute(Attributes.USER_ID)).toString();
	}

	@RequestMapping(value = "/miniatureInInv/{imageName}", method = RequestMethod.GET)
	@ResponseBody
	public void displayMiniatureInInvitation(@PathVariable String imageName, HttpServletResponse response, HttpSession session) {
		byte[] imgData = fileRepository.getImage(imageName);
		ImagesUtil.writeImageToResponse(imgData, response, logger);
	}
	
	@RequestMapping(value = "/invitationAccepted/{relationshipId}", method = RequestMethod.GET)
	public void invitationAccepted(@PathVariable int relationshipId, HttpServletResponse response, HttpSession session) {
		relationshipService.acceptInvitation(relationshipId);
	}
	
	@RequestMapping(value = "/invitationDeclined/{relationshipId}", method = RequestMethod.GET)
	public void invitationDeclined(@PathVariable int relationshipId, HttpServletResponse response, HttpSession session) {
		relationshipService.declineInvitation(relationshipId);
	}

}
