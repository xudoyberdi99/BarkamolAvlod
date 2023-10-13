package com.company.controller;

import com.company.entity.Subject;
import com.company.entity.Teacher;
import com.company.payload.ApiResponse;
import com.company.payload.SubjectDto;
import com.company.payload.TeacherDto;
import com.company.service.TeacherService;
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
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/teacher/add")
    public ResponseEntity<?> addTeacher(@Valid @RequestBody TeacherDto teacherDto){
        ApiResponse apiResponse=teacherService.addTeacher(teacherDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/teacher/edit/{id}")
    public HttpEntity<?> editTeacher(@Valid @PathVariable Long id, @RequestBody TeacherDto teacherDto){
        ApiResponse apiResponse=teacherService.editTeacher(id,teacherDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/teacher/delete/{id}")
    public HttpEntity<?> deleteTeacher(@PathVariable Long id){
        ApiResponse apiResponse=teacherService.deleteTeacher(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @GetMapping("/user/teacher/all")
    public HttpEntity<?> getallTeacher(){
        List<Teacher> teachers=teacherService.getAllTeacher();
        return ResponseEntity.ok(teachers);
    }
    @GetMapping("/user/teacher/allWithPage")
    public HttpEntity<?> getallTeacherWithPage(int page, int size){
        Page<Teacher> teacherPage=teacherService.getAllTeacherWithPage(page,size);
        return ResponseEntity.ok(teacherPage);
    }
    @GetMapping("/user/teacher/TeacherById/{id}")
    public HttpEntity<?> TeacherById(@PathVariable Long id){
        Teacher teacher=teacherService.TeacherById(id);
        return ResponseEntity.ok(teacher);
    }
}
