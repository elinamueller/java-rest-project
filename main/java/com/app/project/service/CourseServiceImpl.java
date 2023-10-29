package com.app.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.project.entity.Course;
import com.app.project.exception.ResourceNotFoundException;
import com.app.project.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseRepository courseRepo;
	
	public List<Course> findCourseByUsername(String username) {
		List<Course> courseList =  new ArrayList<>();
		
		for(Course course : (List<Course>) courseRepo.findAll()) {
			if(course.getUsername().equals(username))
				courseList.add(course);
		}
		return courseList;
	}
	
	public void addCourse(Course course) {
		courseRepo.save(course);
	}
	
	public void deleteCourseById(Long id) {
		if(!courseRepo.existsById(id)) {
			throw new ResourceNotFoundException(
				"Course with id " + id + " does not exist.");
		}
		courseRepo.deleteById(id);
	}

	public Course findCourseById(Long id) {
		return courseRepo.findById(id).get();
	}
	
	public void updateCourse(Course course) {
		Course existCourse = courseRepo.findById(course.getId()).get();
		existCourse.setDescription(course.getDescription());
		existCourse.setTargetDate(course.getTargetDate());
		courseRepo.save(course);
	}

}
