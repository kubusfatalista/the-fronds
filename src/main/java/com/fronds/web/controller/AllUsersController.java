package com.fronds.web.controller;

import java.util.List;

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

import com.fronds.dto.FrondDto;
import com.fronds.service.RelationshipService;
import com.fronds.service.UserService;
import com.fronds.util.Attributes;
import com.fronds.util.FileRepository;
import com.fronds.util.ImagesUtil;

/**
 * Created by Qbek on 2016-12-15.
 */
@Controller
@Secured({ "ROLE_REGULAR", "ROLE_ADMIN" })
@RequestMapping("/allUsers")
public class AllUsersController {
	
	private static final Logger logger = Logger.getLogger(AllUsersController.class);

    @Autowired
    UserService userService;
    
    @Autowired
    RelationshipService relationshipService;
    
    @Autowired
    @Qualifier("localFileRepository")
    FileRepository fileRepository;

    @RequestMapping(method= RequestMethod.GET)
    public List<FrondDto> users(Model model, HttpSession session) {
		if (!model.containsAttribute("user")) {
			model.addAttribute(userService.getUserById((int) session.getAttribute(Attributes.USER_ID)));
		}
        return userService.getAllUsersWithFrondsStatusesList((int) session.getAttribute(Attributes.USER_ID));
    }
    
	@RequestMapping(value = "imageDisplay/{imageName}", method = RequestMethod.GET)
	@ResponseBody
	public void showAlbumImage(@PathVariable String imageName, HttpServletResponse response) {
		byte[] imgData = fileRepository.getImage(imageName);
		ImagesUtil.writeImageToResponse(imgData, response, logger);
	}
	
	@RequestMapping(value = "sendFriendInvitation/{friendId}", method = RequestMethod.GET)
	public String sendFriendInvitation(@PathVariable int friendId, HttpSession session) {
		relationshipService.sendFriendRequest((int) session.getAttribute(Attributes.USER_ID), friendId);
		return "redirect:/myProfile";
	}

}
