package com.app.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.app.project.entity.User;

@Controller
public class MainController {
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	//---sign-up without account/authorization---
	@GetMapping("signup")
	public String goToSignUpForm(ModelMap model) {
		model.put("user", new User());
		return "signup";
	}

}
