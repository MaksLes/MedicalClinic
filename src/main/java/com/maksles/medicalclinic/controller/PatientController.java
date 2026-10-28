package com.maksles.medicalclinic.controller;


import com.maksles.medicalclinic.model.Patient;
import com.maksles.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")

@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    //GET /patients
    @GetMapping
    List<Patient> getPatients() {
        return patientService.getPatients();
    }

    //GET /patients/jan@kowalski.pl
    @GetMapping("/{email}")
    public Patient getPatient(@PathVariable String email) {
        return patientService.getPatient(email);
    }

    //POST /patients (dane pacjenta w body jako JSON)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201 zamiast domyślnego 200
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    //DELETE /patients/jan@kowalski.pl
    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 usunięto, brak treści w odpowiedzi
    public void deletePatient(@PathVariable String email) {
        patientService.deletePaTient(email);
    }

    //PUT /patients/jan@kowalski.pl (nowe dane w body)
    @PutMapping("/{email}")
    public Patient updatePatient(@PathVariable String email, @RequestBody Patient patient) {
        return patientService.updatePatient(email, patient);
    }
}
