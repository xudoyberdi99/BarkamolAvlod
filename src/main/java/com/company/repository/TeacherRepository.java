package com.company.repository;

import com.company.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query(value = "SELECT EXISTS(SELECT * FROM teacher WHERE phone_number=:phoneNumber and not id=:id)",nativeQuery = true)
    boolean existsByPhoneNumberAndNotId(String phoneNumber,Long id);
}
