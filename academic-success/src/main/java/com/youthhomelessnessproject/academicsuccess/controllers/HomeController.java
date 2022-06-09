package com.youthhomelessnessproject.academicsuccess.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    public String showHomePage() { return "homepage"; }

}
