package com.company.repository;

import com.company.entity.BAMS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BAMSRepository extends JpaRepository<BAMS,Long> {
    Boolean existsByPhoneNumber(String phoneNumber);
    @Query(value = "SELECT EXISTS(SELECT * FROM bams WHERE phone_number=:phoneNumber and not id=:id)",nativeQuery = true)
    boolean existsByPhoneNumberAndnotId(String phoneNumber,Long id);
}
