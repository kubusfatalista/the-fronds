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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fronds.domain.model.User;
import com.fronds.service.UserPhotoService;
import com.fronds.service.UserService;
import com.fronds.util.Attributes;
import com.fronds.util.FileRepository;
import com.fronds.util.ImagesUtil;

@Controller
@Secured({ "ROLE_REGULAR", "ROLE_ADMIN" })
@RequestMapping("/myAlbums")
public class MyAlbumsController {
	
	private static final Logger logger = Logger.getLogger(MyAlbumsController.class);
	
	@Autowired
	ServletContext context;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserPhotoService userPhotoService;
	
	@Autowired
	@Qualifier("localFileRepository")
	FileRepository fileRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public String albums(Model model, HttpSession session) {
		User user = userService.getUserById((int) session.getAttribute(Attributes.USER_ID));
		if (!model.containsAttribute("user")) {
			model.addAttribute(user);
		}
		model.addAttribute(userPhotoService.getAllUserPhotos(user.getUserId()));
		return "myAlbums";
	}

	@RequestMapping(value = "imageDisplay/{imageName}", method = RequestMethod.GET)
	@ResponseBody
	public void showAlbumImage(@PathVariable String imageName, HttpServletResponse response) {
		byte[] imgData = fileRepository.getImage(imageName);
		ImagesUtil.writeImageToResponse(imgData, response, logger);
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
