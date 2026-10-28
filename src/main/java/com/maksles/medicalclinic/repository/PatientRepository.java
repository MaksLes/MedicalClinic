package com.maksles.medicalclinic.repository;

import com.maksles.medicalclinic.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepository {

    private final List<Patient> patients = new ArrayList<>();

    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    //Optional = "może być pacjent, a moze go nie byc"
    public Optional<Patient> findByEmail(String email) {
        return patients.stream()
                .filter(patient -> patient.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Patient save(Patient patient) {
        patients.add(patient);
        return patient;
    }

    //zwraca true, jesli cos faktycznie zostało usuniete
    public boolean deleteByEmail(String email) {
        return patients.removeIf(patient -> patient.getEmail().equalsIgnoreCase(email));
    }

}
