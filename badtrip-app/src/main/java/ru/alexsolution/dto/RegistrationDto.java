package ru.alexsolution.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class RegistrationDto {

    private UUID id;

//    @NotEmpty(message = "Логин не может быть пустым")
//    @Min(value = 3, message = "Логин должен быть больше трех символов")
//    @Max(value = 12, message = "Логин не может быть больше двенатцати символов")
////    @Pattern(regexp = "[a-zA-Z]")
    private String login;

//    @NotEmpty(message = "Имя не может быть пустым")
//    @Min(value = 2, message = "Имя должно быть больше двух символов")
//    @Max(value = 12, message = "Имя не может быть больше двенатдцати символов")
//    @Pattern(regexp = "[a-zA-Z,а-яА-Я]")
    private String firstName;

//    @NotEmpty(message = "Фамилия не может быть пустой")
//    @Min(value = 2, message = "Фамилия должна быть больше двух символов")
//    @Max(value = 15, message = "Фамилия не может быть больше двенатдцати символов")
//    @Pattern(regexp = "[a-zA-Z,а-яА-Я]")
    private String lastName;

    private LocalDate dateOfBirth;

//    @NotEmpty(message = "Название города не может быть пустым")
//    @Min(value = 2, message = "Название города не может быть меньше двух символов")
//    @Max(value = 15, message = "Название города не может быть больше пятнадцати символов")
//    @Pattern(regexp = "[a-zA-Z,а-яА-Я]")
    private String city;

    private String avatar;

    private String aboutMe;

//    @NotEmpty(message = "Пароль не может быть пустым")
//    @Min(value = 6, message = "Длина пароля не может быть меньше шести символов")
//    @Max(value = 30, message = "Длина пароля не может быть больше тридцати символов")
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\\\S+$)")
    private String password;
}
