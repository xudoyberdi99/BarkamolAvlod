package com.company.service;

import com.company.entity.Subject;
import com.company.payload.ApiResponse;
import com.company.payload.SubjectDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SubjectService {
    ApiResponse addSubject(SubjectDto subjectDto);

    ApiResponse editSubject(Long id, SubjectDto subjectDto);

    ApiResponse deleteSubject(Long id);

    List<Subject> getAllSubject();

    Page<Subject> getAllSubjectWithPage(int page, int size);

    Subject SubjectById(Long id);

    List<Subject> SubjectByCourseId(Long courseId);
}
