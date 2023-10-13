package com.company.service;

import com.company.entity.Teacher;
import com.company.payload.ApiResponse;
import com.company.payload.TeacherDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService {
    ApiResponse addTeacher(TeacherDto teacherDto);

    ApiResponse editTeacher(Long id, TeacherDto teacherDto);

    ApiResponse deleteTeacher(Long id);

    List<Teacher> getAllTeacher();

    Page<Teacher> getAllTeacherWithPage(int page, int size);

    Teacher TeacherById(Long id);
}
