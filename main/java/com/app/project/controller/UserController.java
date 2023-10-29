package com.app.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.project.entity.Role;
import com.app.project.entity.User;
import com.app.project.service.UserService;
import com.app.project.service.UserService;

@Controller
@SessionAttributes("username")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("signup")
	public String userProfile(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) {
		model.put("user", user);
		model.put("username", user.getUsername());
		if (result.hasErrors()) {
			return "signup";
		}
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setRole(Role.USER);
		userService.saveProfile(user);
		return "user/user_profile";
	}
	
	@GetMapping("accounts/user/login")
	public String viewUserLoginPage() {
		return "user/user_login";
	}
	
	@GetMapping("accounts/user/profile")
	public String viewUserProfilePage(ModelMap model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getProfileByUsername(userDetails.getUsername());
		model.put("username", user.getUsername());
		model.addAttribute("user", user);
		return "user/user_profile";
	}
	
	@GetMapping("accounts/user/profile/edit")
	public String goToEditProfilePage(ModelMap model) {
		String userName = (String) model.get("username");
		model.put("user", userService.getProfileByUsername(userName));
		return "user/user_signup_edit";
	}
	
	@PostMapping("accounts/user/profile/edit")
	public String saveProfileChanges(ModelMap model, User user) {
		String userName = (String) model.get("username");
		User updatedUser = userService.updateProfile(userName, user);
		model.addAttribute("user", updatedUser);
		return "user/user_profile";
	}

}
