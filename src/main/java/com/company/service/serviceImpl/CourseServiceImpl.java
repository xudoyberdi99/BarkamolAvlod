package com.company.service.serviceImpl;

import com.company.entity.Course;
import com.company.payload.ApiResponse;
import com.company.repository.CourseRepository;
import com.company.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public ApiResponse addCourse(Course course) {
        Boolean existsByName = courseRepository.existsByName(course.getName());
        if (existsByName){
            return new ApiResponse("already exists course",false);
        }
        Course course1=new Course();
        course1.setName(course.getName());

        courseRepository.save(course1);
        return new ApiResponse("add course success",true);
    }

    @Override
    public ApiResponse editCourse(Long id, Course course) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (!courseOptional.isPresent()){
            return new ApiResponse("not found course", false);
        }
        Course course1 = courseOptional.get();
        course1.setName(course.getName());

        courseRepository.save(course1);
        return new ApiResponse("edit course success", true);
    }

    @Override
    public ApiResponse deleteCourse(Long id) {
        try{
            courseRepository.deleteById(id);
            return new ApiResponse("delete course",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Page<Course> getAllCourseWithPage(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return courseRepository.findAll(pageable);
    }

    @Override
    public Course CourseById(Long id) {
        return courseRepository.findById(id).orElse(new Course());
    }
}
