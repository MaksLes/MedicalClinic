package com.maksles.medicalclinic.controller;


import com.maksles.medicalclinic.model.Patient;
import com.maksles.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")

@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    List<Patient> getPatients() {
        return patientService.getPatients();
    }
}
