package ru.alexsolution.controllers;

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
public class RegistrationController {
    private final UserService userService;

    @PostMapping
    public void registration(@Valid @RequestBody RegistrationDto registrationDto){
        userService.saveNewUser(registrationDto);
    }

}
