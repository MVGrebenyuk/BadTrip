package ru.alexsolution.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexsolution.dto.RegistrationDto;
import ru.alexsolution.entity.User;
import ru.alexsolution.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@Tag(name = "REGISTER API", description = "Контроллер для регистрации")
public class ProfileController {
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Добавить пользователя")
    public User registration(@RequestBody RegistrationDto registrationDto){
        return userService.saveNewUser(registrationDto);
    }

    @PostMapping("/update")
    @Operation(summary = "Обновить профиль пользователя")
    public void updateProfile(Principal principal, @RequestBody RegistrationDto updateProfileDto){
        userService.updateUser(principal, updateProfileDto);
    }
}
