package com.app.project.service;

import java.util.List;

import com.app.project.entity.Course;

public interface CourseService {
	
	List<Course> findCourseByUsername(String username);
	
	void addCourse(Course course);
	
	void deleteCourseById(Long id);
	
	Course findCourseById(Long id);
	
	void updateCourse(Course course);

}
