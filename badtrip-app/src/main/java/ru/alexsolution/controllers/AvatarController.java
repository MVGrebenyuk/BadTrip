package ru.alexsolution.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.alexsolution.services.UserService;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AvatarController {

    private final UserService userService;

    @PutMapping("/addAvatar")
    public void addAvatar(Principal principal, MultipartFile multipartFile) throws IOException {
        userService.saveAvatar(principal, multipartFile);
    }
}
