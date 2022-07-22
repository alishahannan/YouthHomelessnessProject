package com.youthhomelessnessproject.academicsuccess.controllers;

import org.springframework.stereotype.Controller;
import com.youthhomelessnessproject.academicsuccess.controllers.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ZipCodeController {
	
	 @GetMapping("/survey/zipcode")
	    public String showPage()
	    {
	    	return "survey-zipcode";
	    }
	   
	    @PostMapping("/survey/zipcode/submit")
	    public String requestBody(@RequestBody String requestBody)
	    {
	    	String zipCode = requestBody.split("=")[1];
	    	
	    	return "redirect:/survey?zipcode=" + zipCode;
	    }
	    

}
