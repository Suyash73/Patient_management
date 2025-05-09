package com.pm.repository;

import com.pm.model.PatientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends MongoRepository<PatientEntity, String> {
    boolean existsByEmail(String email);
}
