package com.fronds.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Qbek on 2016-12-11.
 */
@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {
    @RequestMapping(method=GET)
    public String home() {
        return "home";
    }
}