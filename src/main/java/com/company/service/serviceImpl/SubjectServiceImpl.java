package com.company.service.serviceImpl;

import com.company.entity.Course;
import com.company.entity.Subject;
import com.company.payload.ApiResponse;
import com.company.payload.SubjectDto;
import com.company.repository.CourseRepository;
import com.company.repository.SubjectRepository;
import com.company.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public ApiResponse addSubject(SubjectDto subjectDto) {
        Boolean existsByName = subjectRepository.existsByName(subjectDto.getName());
        if (existsByName){
            return new ApiResponse("already exists subject",false);
        }
        Optional<Course> optionalCourse = courseRepository.findById(subjectDto.getCourseId());
        if (!optionalCourse.isPresent()){
            return new ApiResponse("not found course",false);
        }
        Subject subject=new Subject();
        subject.setName(subjectDto.getName());
        subject.setDescription(subjectDto.getDescription());
        subject.setCourse(optionalCourse.get());

        subjectRepository.save(subject);

        return new ApiResponse("add subject success",true);
    }

    @Override
    public ApiResponse editSubject(Long id, SubjectDto subjectDto) {
        boolean exists = subjectRepository.existsByNameAndItNot(subjectDto.getName(), id);
        if (exists){
            return new ApiResponse("already exists subject",false);
        }
        Optional<Course> optionalCourse = courseRepository.findById(subjectDto.getCourseId());
        if (!optionalCourse.isPresent()){
            return new ApiResponse("not found course",false);
        }
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (!optionalSubject.isPresent()){
            return new ApiResponse("not found subject",false);
        }
        Subject subject = optionalSubject.get();
        subject.setCourse(optionalCourse.get());
        subject.setName(subjectDto.getName());
        subject.setDescription(subjectDto.getDescription());
        subjectRepository.save(subject);
        return new ApiResponse("edit subject success",true);
    }

    @Override
    public ApiResponse deleteSubject(Long id) {
        try{
            subjectRepository.deleteById(id);
            return new ApiResponse("delete Subject",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    @Override
    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    @Override
    public Page<Subject> getAllSubjectWithPage(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return subjectRepository.findAll(pageable);
    }

    @Override
    public Subject SubjectById(Long id) {
        return subjectRepository.findById(id).orElse(new Subject());
    }

    @Override
    public List<Subject> SubjectByCourseId(Long courseId) {
        List<Subject> subjectList = subjectRepository.subjectListByCourseId(courseId);
        return subjectList;
    }
}
