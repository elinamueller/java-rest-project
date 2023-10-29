package com.app.project.runner;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.app.project.entity.Course;
import com.app.project.repository.CourseRepository;

@Component
@Order(2)
public class CourseRunner implements CommandLineRunner {
	
	@Autowired
	private CourseRepository courseRepo;

	@Override
	public void run(String... args) throws Exception {
		courseRepo.save(new Course(101L,"captainpicard","Astrobiology", LocalDate.now().plusYears(2), false));
		courseRepo.save(new Course(202L,"zaphodfourth","Archeology", LocalDate.now().plusYears(3), false));
		courseRepo.save(new Course(303L,"captainpicard","Physics", LocalDate.now().plusYears(3), false));
		courseRepo.save(new Course(404L,"zaphodfourth","Machine Learning", LocalDate.now().minusYears(1), true));
		courseRepo.save(new Course(505L,"zaphodfourth","Cosmoculinary", LocalDate.now().minusYears(1), true));
	}

}
