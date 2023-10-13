package com.company.repository;

import com.company.entity.Connect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConnectRepository extends JpaRepository<Connect,Long> {
    Boolean existsByPhoneNumber(String phoneNumber);
    @Query(value = "SELECT EXISTS(SELECT * FROM connect WHERE phone_number=:phoneNumber and not id=:id)",nativeQuery = true)
    boolean existsByPhoneNumberAndNotId(String phoneNumber,Long id);
}
