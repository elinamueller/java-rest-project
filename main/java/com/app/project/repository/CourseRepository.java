package com.app.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.project.entity.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}
