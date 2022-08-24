package ru.alexsolution.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexsolution.dto.RegistrationDto;
import ru.alexsolution.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
@Tag(name = "REGISTER API", description = "Контроллер для регистрации")
public class RegistrationController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Добавить пользователя")
    public void registration(@Valid @RequestBody RegistrationDto registrationDto){
        userService.saveNewUser(registrationDto);
    }
}
