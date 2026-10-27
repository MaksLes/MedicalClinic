package com.maksles.medicalclinic.service;

import com.maksles.medicalclinic.model.Patient;
import com.maksles.medicalclinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;

    public List<Patient> getPatients() {
        return repository.getPatients();
    }
}
