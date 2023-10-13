package com.company.controller;

import com.company.entity.Subject;
import com.company.payload.ApiResponse;
import com.company.payload.SubjectDto;
import com.company.service.SubjectService;
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
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping("/subject/add")
    public ResponseEntity<?> addSubject(@Valid @RequestBody SubjectDto subjectDto){
        ApiResponse apiResponse=subjectService.addSubject(subjectDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/subject/edit/{id}")
    public HttpEntity<?> editSubject(@Valid @PathVariable Long id, @RequestBody SubjectDto subjectDto){
        ApiResponse apiResponse=subjectService.editSubject(id,subjectDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/subject/delete/{id}")
    public HttpEntity<?> deleteSubject(@PathVariable Long id){
        ApiResponse apiResponse=subjectService.deleteSubject(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @GetMapping("/user/subject/all")
    public HttpEntity<?> getallSubject(){
        List<Subject> subjectList=subjectService.getAllSubject();
        return ResponseEntity.ok(subjectList);
    }
    @GetMapping("/user/subject/allWithPage")
    public HttpEntity<?> getallSubjectWithPage(int page, int size){
        Page<Subject> subjects=subjectService.getAllSubjectWithPage(page,size);
        return ResponseEntity.ok(subjects);
    }
    @GetMapping("/user/subject/subjectById/{id}")
    public HttpEntity<?> SubjectById(@PathVariable Long id){
        Subject subject=subjectService.SubjectById(id);
        return ResponseEntity.ok(subject);
    }
    @GetMapping("/user/subject/subjectListCourseId/{courseId}")
    public HttpEntity<?> SubjectByCourseId(@PathVariable Long courseId){
        List<Subject> subjectList=subjectService.SubjectByCourseId(courseId);
        return ResponseEntity.ok(subjectList);
    }
}
