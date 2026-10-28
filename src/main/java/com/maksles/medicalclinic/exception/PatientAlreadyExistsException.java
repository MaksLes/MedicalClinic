package com.maksles.medicalclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PatientAlreadyExistsException extends RuntimeException {

    public PatientAlreadyExistsException(String email) {
        super("Pacjent o emailu" + email + " już istnieje");
    }
}
