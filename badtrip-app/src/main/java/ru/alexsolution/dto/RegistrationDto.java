package ru.alexsolution.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class RegistrationDto {

    private UUID id;

    private String login;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String city;

    private String avatar;

    private String aboutMe;

    private String password;
}
