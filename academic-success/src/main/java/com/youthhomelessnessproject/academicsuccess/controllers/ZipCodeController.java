package com.youthhomelessnessproject.academicsuccess.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ZipCodeController {

	@GetMapping("/survey/zipcode")
	public String showPage() {
		return "survey-zipcode";
	}

	@PostMapping("/survey/zipcode/submit")
	public String requestBody(@RequestBody String requestBody) {
		String zipCode = requestBody.split("=")[1];

		if (isValidZip(zipCode)) {
			return "redirect:/survey?zipcode=" + zipCode;
		}

		return "redirect:/survey/zipcode?error";
	}

	private boolean isValidZip(String zip) {
		int zipTally = 0;

		if(zip.length() != 5) {
			return false;
		}

		for (char c : zip.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			} else {
				zipTally += ((int) c) - 48; // ASCII 0 == 48
			}
		}

		// Valid US Zips: 00001 - 99950
		if(zipTally < 1) {
			return false;
		}

		if(zip.startsWith("999")) {
			int fourth = ((int) zip.charAt(3)) - 48;
			int fifth = ((int) zip.charAt(4)) - 48;

			if(fourth > 5) {
				return false;
			} else if (fourth == 5 && fifth != 0) {
				return false;
			}
		}
		return true;
	}

}
