package com.company.service.serviceImpl;

import com.company.entity.Attachment;
import com.company.entity.Subject;
import com.company.entity.Teacher;
import com.company.payload.ApiResponse;
import com.company.payload.TeacherDto;
import com.company.repository.AttachmentRepository;
import com.company.repository.SubjectRepository;
import com.company.repository.TeacherRepository;
import com.company.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public ApiResponse addTeacher(TeacherDto teacherDto) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(teacherDto.getImageId());
        if(!optionalAttachment.isPresent()){
            return new ApiResponse("not found image", false);
        }
        List<Long> subjectIds=teacherDto.getSubjects();
        List<Subject> subjectList = subjectRepository.findAllById(subjectIds);

        Teacher teacher=new Teacher();
        teacher.setFullName(teacherDto.getFullName());
        teacher.setAchievements(teacherDto.getAchievements());
        teacher.setImage(optionalAttachment.get());
        teacher.setSubjects(subjectList);
        teacher.setPhoneNumber(teacherDto.getPhoneNumber());

        teacherRepository.save(teacher);
        return new ApiResponse("add teacher success", true);
    }

    @Override
    public ApiResponse editTeacher(Long id, TeacherDto teacherDto) {
        boolean exists = teacherRepository.existsByPhoneNumberAndNotId(teacherDto.getPhoneNumber(), id);
        if (exists){
            return new ApiResponse("already exists phoneNumber",false);
        }
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (!optionalTeacher.isPresent()){
            return new ApiResponse("not found teacher",false);
        }
        List<Subject> subjectList = subjectRepository.findAllById(teacherDto.getSubjects());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(teacherDto.getImageId());
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("not found image",false);
        }
        Teacher teacher = optionalTeacher.get();
        teacher.setPhoneNumber(teacherDto.getPhoneNumber());
        teacher.setFullName(teacherDto.getFullName());
        teacher.setImage(optionalAttachment.get());
        teacher.setSubjects( subjectList);
        teacher.setAchievements(teacherDto.getAchievements());

        teacherRepository.save(teacher);
        return new ApiResponse("edit teacher success",true);
    }

    @Override
    public ApiResponse deleteTeacher(Long id) {
        try{
            teacherRepository.deleteById(id);
            return new ApiResponse("delete teacher",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public Page<Teacher> getAllTeacherWithPage(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return teacherRepository.findAll(pageable);
    }

    @Override
    public Teacher TeacherById(Long id) {
        return teacherRepository.findById(id).orElse(new Teacher());
    }
}
