package com.fronds.web.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fronds.domain.model.ReactionType;
import com.fronds.service.CommentService;
import com.fronds.service.ReactionService;
import com.fronds.util.Attributes;

@Controller
@Secured({ "ROLE_REGULAR", "ROLE_ADMIN" })
@RequestMapping("/comments")
public class CommentsController {
	
	@Autowired
	private CommentService commentsService;
	
	@Autowired
	private ReactionService reactionService;
	
	@RequestMapping(value = "/{statusId}", method = RequestMethod.GET)
	public String getComments(@PathVariable int statusId, Model model, HttpSession session) {
		model.addAttribute("timeMooseStatusId", statusId);
		model.addAttribute(commentsService.getCommentsForStatus(statusId));
		return "statuses :: commentsList";
	}

	@RequestMapping(value = "/createComment", method = RequestMethod.GET)
	@ResponseBody
	public void createComment(@RequestParam("commentText") String commentText, 
			@RequestParam("statusId") int statusId, Model model, HttpSession session) {
		commentsService.createComment(commentText, statusId, (int) session.getAttribute(Attributes.USER_ID));
	}
	
	@RequestMapping(value = "/lajk/{statusId}", method = RequestMethod.GET)
	public void likeStatus(@PathVariable int statusId, HttpServletResponse response, HttpSession session) {
		reactionService.createReaction(ReactionType.LIKE, statusId, (int) session.getAttribute(Attributes.USER_ID));
	}
	
	@RequestMapping(value = "/hejt/{statusId}", method = RequestMethod.GET)
	public void hateStatus(@PathVariable int statusId, HttpServletResponse response, HttpSession session) {
		reactionService.createReaction(ReactionType.HATE, statusId, (int) session.getAttribute(Attributes.USER_ID));
	}

}
