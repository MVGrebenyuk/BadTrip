package ru.alexsolution.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexsolution.entity.user.User;
import ru.alexsolution.services.UserService;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "USER API", description = "Контроллер для работы с пользователями")
public class UserController {

    private final UserService service;

    @GetMapping
    @Operation(description = "Получение пользоавтеля из токена")
    public User getUser(Principal principal){
        if(principal == null){
            return null;
        }
        return service.findByLogin(principal.getName()).orElseThrow();
    }

    @GetMapping("/{userId}")
    @Operation(description = "Получение пользователя по User ID")
    public User getUser(@PathVariable String userId){
        return service.findByID(UUID.fromString(userId));
    }
}
