package com.fhunhospital.patient_management.repositories;

import com.fhunhospital.patient_management.entites.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String > {
}
