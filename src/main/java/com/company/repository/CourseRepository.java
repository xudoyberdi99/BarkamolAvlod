package com.company.repository;

import com.company.entity.Course;
import com.company.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Boolean existsByName(String name);

}
