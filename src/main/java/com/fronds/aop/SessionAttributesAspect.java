package com.fronds.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fronds.util.Attributes;
import com.fronds.util.CustomUser;

@Component
@Aspect
public class SessionAttributesAspect {
	
	@Before(" execution(* com.fronds.web.controller.*.*(..))")
	public void addUserIdToSessionIfNecessary(JoinPoint joinPoint) {
		for(Object arg : joinPoint.getArgs()) {
			if(arg instanceof HttpSession) {
				HttpSession session = (HttpSession) arg;
				if (session.getAttribute(Attributes.USER_ID) == null) {
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					CustomUser customUser = (CustomUser) auth.getPrincipal();
					session.setAttribute(Attributes.USER_ID, customUser.getId());	
					session.setAttribute("userName", customUser.getUsername());
				}
				break;
			}
		}
	}
}
