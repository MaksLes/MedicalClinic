package com.maksles.medicalclinic.dto;

// record = niezmienna klasa na dane a jackson sam zmapuje {"password": "..."}
public record ChangePasswordRequest(String password) {
}
