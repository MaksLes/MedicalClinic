package com.maksles.medicalclinic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data // generuje gettery, settery, equals, hashCode i toString
@NoArgsConstructor // pusty konstruktor, potrzebny Jacksonowi do odczytu JSON-a
@AllArgsConstructor // konstruktor ze wszystkimi polami
public class Patient {

    private String email;

    // WRITE_ONLY hasło można wysłać w żądaniu, ale nigdy nie wraca w odpowiedzi
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String idCardNo;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private LocalDate birthday; // format w JSON-ie yyyy-MM-dd
}
