package com.fronds.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Qbek on 2016-12-11.
 */
@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {
    @RequestMapping(method=RequestMethod.GET)
    public String home() {
        return "home";
    }
    
    @RequestMapping(value="/myProfileTest", method=RequestMethod.GET)
    public String getTestProfilePage() {
    	return "myProfileTest";
    }
}