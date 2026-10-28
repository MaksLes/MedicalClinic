package com.maksles.medicalclinic.service;

import com.maksles.medicalclinic.exception.PatientAlreadyExistsException;
import com.maksles.medicalclinic.exception.PatientNotFoundException;
import com.maksles.medicalclinic.model.Patient;
import com.maksles.medicalclinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;

    //wyswietlenie danych wszystkich pacjentow
    public List<Patient> getPatients() {
        return repository.getPatients();
    }

    //wyswietlenie danych konkretnego pacjenta poprzez email
    public Patient getPatient(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException(email));
    }

    //dodanie nowego pacjenta (email musi byc unikalny)
    public Patient addPatient(Patient patient) {
        if (repository.findByEmail(patient.getEmail()).isPresent()) {
            throw new PatientAlreadyExistsException(patient.getEmail());
        }
        return repository.save(patient);
    }

    //usuniecie konkretnego pacjenta poprzez email
    public void deletePaTient(String email) {
        if (!repository.deleteByEmail(email)) {
            throw new PatientNotFoundException(email);
        }
    }

    //edycja konkretnego pacjenta poprzez email
    public Patient updatePatient(String email, Patient updatedPatient) {
        Patient existing = getPatient(email);

        existing.setPassword(updatedPatient.getPassword());
        existing.setIdCardNo(updatedPatient.getIdCardNo());
        existing.setFirstName(updatedPatient.getFirstName());
        existing.setLastName(updatedPatient.getLastName());
        existing.setPhoneNumber(updatedPatient.getPhoneNumber());
        existing.setBirthday(updatedPatient.getBirthday());

        return existing;
    }
}
