package com.company.repository;

import com.company.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Boolean existsByName(String name);
    @Query(value = "SELECT EXISTS(SELECT * FROM subject WHERE name=:name and not id=:id)",nativeQuery = true)
    boolean existsByNameAndItNot(String name,Long id);
    @Query(value = "SELECT * FROM subject WHERE course_id=:course_id ",nativeQuery = true)
    List<Subject> subjectListByCourseId(Long course_id);
}
