package com.maksles.medicalclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 400 Bad Request: dane od klienta łamią reguły biznesowe
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PatientValidationException extends RuntimeException {

    public PatientValidationException(String message) {
        super(message);
    }
}
