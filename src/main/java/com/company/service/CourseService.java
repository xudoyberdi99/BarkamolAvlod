package com.company.service;

import com.company.entity.Course;
import com.company.payload.ApiResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    ApiResponse addCourse(Course course);

    ApiResponse editCourse(Long id, Course course);

    ApiResponse deleteCourse(Long id);

    List<Course> getAllCourse();

    Page<Course> getAllCourseWithPage(int page, int size);

    Course CourseById(Long id);
}
