package com.company.controller;

import com.company.entity.Course;
import com.company.entity.Media;
import com.company.payload.ApiResponse;
import com.company.payload.MediaDto;
import com.company.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(value = "*",maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/course/add")
    public ResponseEntity<?> addCourse(@Valid @RequestBody Course course){
        ApiResponse apiResponse=courseService.addCourse(course);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/course/edit/{id}")
    public HttpEntity<?> editCourse(@Valid @PathVariable Long id, @RequestBody Course course){
        ApiResponse apiResponse=courseService.editCourse(id,course);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/course/delete/{id}")
    public HttpEntity<?> deleteCourse(@PathVariable Long id){
        ApiResponse apiResponse=courseService.deleteCourse(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @GetMapping("/user/course/all")
    public HttpEntity<?> getallCourse(){
        List<Course> courseList=courseService.getAllCourse();
        return ResponseEntity.ok(courseList);
    }
    @GetMapping("/user/course/allWithPage")
    public HttpEntity<?> getallCourseWithPage(int page, int size){
        Page<Course> courses=courseService.getAllCourseWithPage(page,size);
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/user/course/courseById/{id}")
    public HttpEntity<?> CourseById(@PathVariable Long id){
        Course course=courseService.CourseById(id);
        return ResponseEntity.ok(course);
    }
}
