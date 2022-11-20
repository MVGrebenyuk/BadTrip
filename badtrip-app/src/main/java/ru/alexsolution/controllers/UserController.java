package ru.alexsolution.controllers;

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
public class UserController {

    private final UserService service;

    @GetMapping
    public User getUser(Principal principal){
        if(principal == null){
            return null;
        }
        return service.findByLogin(principal.getName()).orElseThrow();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
        return service.findByID(UUID.fromString(userId));
    }
}
