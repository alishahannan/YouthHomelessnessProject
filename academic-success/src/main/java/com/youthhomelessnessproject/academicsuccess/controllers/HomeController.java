package com.youthhomelessnessproject.academicsuccess.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    public String showHomePage() { return "homepage"; }

}
