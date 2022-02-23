package com.samiharoon.apitdd.persistence.repository;

import com.samiharoon.apitdd.persistence.domain.Sample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;


public interface SampleRepository extends JpaRepository<Sample, Long>, JpaSpecificationExecutor<Sample> {
    
    @Query("select s from Sample s where s.id = :id")
    Optional<Sample> findById(Long id);

    @Query("select s from Sample s where s.phoneNumber = :phoneNumber")
    Optional<Sample> findByPhoneNumber(Long phoneNumber);

    @Query( countQuery = "SELECT COUNT (s) FROM Sample s ", value = "SELECT s FROM Sample s")
    Page<Sample> findPage(Pageable pageable);
}
