package com.app.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.project.entity.User;
import com.app.project.service.UserService;

@Controller
@SessionAttributes("username")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	//---admin login and profile management---
	
	@GetMapping("/accounts/admin/login")
	public String viewAdminLoginPage() {
		return "admin/admin_login";
	}
	
	@GetMapping("/accounts/admin/profile")
	public String viewAdminProfilePage(ModelMap model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getProfileByUsername(userDetails.getUsername());
		model.put("username", user.getUsername());
		model.addAttribute("user", user);
		return "admin/admin_profile";
	}
	
	@GetMapping("/accounts/admin/users")
	public String listAllUsers(Model model) {
		List<User> userList = (List<User>) userService.getAllProfiles();
		model.addAttribute("userList", userList);
		return "admin/admin_users";
 	}
	
	@GetMapping("accounts/admin/users/delete")
	public String deleteUser(@RequestParam Long id) {
		userService.deleteProfileById(id);
		return "redirect:/accounts/admin/users";
	}

}
