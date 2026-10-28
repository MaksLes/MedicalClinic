package com.maksles.medicalclinic.service;

import com.maksles.medicalclinic.exception.PatientAlreadyExistsException;
import com.maksles.medicalclinic.exception.PatientNotFoundException;
import com.maksles.medicalclinic.exception.PatientValidationException;
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

    //MED-997 pkt 2: dwoch pacjentów nie może mieć tego samego maila
    public Patient addPatient(Patient patient) {
        if (isBlank(patient.getEmail())) {
            throw new PatientValidationException("Email jest wymagany");
        }
        //findByEmail porownuje porownuje bez wielkosci liter
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

    // edycja nie zmienia emaila (identyfikator), hasła (osobny endpoint), dowodu (MED-997 pkt 1)
    public Patient updatePatient(String email, Patient updatedPatient) {
        Patient existing = getPatient(email);

        //proba podmiany numeru dowodu konczy sie bledem zamiast zignorowania
        if (updatedPatient.getIdCardNo() != null && !updatedPatient.getIdCardNo().equals(existing.getIdCardNo())) {
            throw new PatientValidationException("Numer dowodu nie może zostać zmieniony");
        }

        existing.setFirstName(updatedPatient.getFirstName());
        existing.setLastName(updatedPatient.getLastName());
        existing.setPhoneNumber(updatedPatient.getPhoneNumber());
        existing.setBirthday(updatedPatient.getBirthday());

        return existing;
    }

    //jedyne miejsce w systemie gdzie mozna zmienic haslo
    public void changePassword(String email, String newPassword) {
        if (isBlank(newPassword)) {
            throw new PatientValidationException("Hasło nie może pozostać puste");
        }
        Patient patient = getPatient(email);
        patient.setPassword(newPassword);
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
