package ru.alexsolution.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.alexsolution.services.UserService;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Tag(name = "AVATAR API", description = "Контроллер аватаров пользователей")
public class AvatarController {

    private final UserService userService;

    @PutMapping("/addAvatar")
    public void addAvatar(Principal principal, MultipartFile multipartFile) throws IOException {
        userService.saveAvatar(principal, multipartFile);
    }
}
