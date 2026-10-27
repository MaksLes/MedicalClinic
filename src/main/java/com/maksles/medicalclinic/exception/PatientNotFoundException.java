package com.maksles.medicalclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//Spring automatycznie zwroci kod 404, gdy ten wyjątek zostanie rzucony
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(String email) {
        super("Pacjent z emailem" + email + " nie został znaleziony");
    }
}
