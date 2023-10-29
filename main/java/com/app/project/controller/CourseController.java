package com.app.project.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.app.project.entity.Course;
import com.app.project.service.CourseService;

@Controller
@RequestMapping("/accounts/user")
@SessionAttributes("username")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@GetMapping("/courses")
	public String listAllCourses(ModelMap model, Principal principal) {
		String username = principal.getName();
		model.put("username", username); 
		List<Course> courses = courseService.findCourseByUsername(username);
		model.addAttribute("courses", courses);
		return "user/list_courses";
	}

	@GetMapping("/courses/add")
	public String showNewCoursePage(ModelMap model) {
		String username = (String)model.get("username");
		Course course = new Course();
		model.put("course", course);
		return "user/add_course";
	}

	@PostMapping("/courses/add")
	public String addNewCourse(@Valid @ModelAttribute("course") Course course, 
							BindingResult result, ModelMap model, Principal principal) {
		if(result.hasErrors()) {
			return "user/add_course";
		}
		course.setUsername(principal.getName());
		courseService.addCourse(course);
		return "redirect:/accounts/user/courses";
	}

	@GetMapping("/courses/delete")
	public String deleteCourse(@RequestParam Long id) {
		courseService.deleteCourseById(id);
		return "redirect:/accounts/user/courses";
	}

	@GetMapping("/courses/update")
	public String updateCourse(@RequestParam Long id, ModelMap model) {
		Course course = courseService.findCourseById(id);
		model.addAttribute("course", course);
		return "user/update_course";
	}
	
	@PostMapping("/courses/update")
	public String updateCourse(ModelMap model, @Valid Course course, BindingResult result) {
		if(result.hasErrors()) {
			return "user/update_course";
		}
		String username = (String)model.get("username");
		course.setUsername(username);
		course.setTargetDate(course.getTargetDate());
		courseService.updateCourse(course);
		return "redirect:/accounts/user/courses";
	}

}
